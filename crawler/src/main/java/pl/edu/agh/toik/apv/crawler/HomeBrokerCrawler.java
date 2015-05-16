package pl.edu.agh.toik.apv.crawler;

import com.google.common.collect.Sets;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.toik.visualisation.database.dao.OfferDAO;
import pl.edu.agh.toik.visualisation.database.dto.Offer;
import pl.edu.agh.toik.visualisation.database.dto.enums.OfferType;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Magda on 2015-05-06.
 */
public class HomeBrokerCrawler {

	private static Logger LOG = Logger.getLogger(HomeBrokerCrawler.class);

	private static final String RENT_START_URL = "https://homebroker.pl/wyniki-wyszukiwania/rynek,pierwotny,wtorny,pokoje,0,rub,wynajem,exclusive,0,strona,1.html";

	private static final String SELL_START_URL = "https://homebroker.pl/kup";

	private Set<String> visitedUrls = new HashSet<String>();

	private List<String> urlsToVisit = new ArrayList<>();

	private FirefoxDriver driver = new FirefoxDriver();

	private int visitedUrlsCount = 0;

	private List<Offer> offers = new ArrayList<Offer>();

	private Set<String> validUrlElements = Sets.newHashSet("oferty", "wynajmij", "kup", "wyniki-wyszukiwania");

	private Set<String> allowedDomains = Sets.newHashSet("https://homebroker.pl/");

	private Set<String> elementsToRemove = Sets.newHashSet("div.header", "div.footerMenu", "div.footerBar");

    private static String stringURI = "postgres://yjgigsqewjnjwr:J7plfUUZwc-c5TUDdo5sm-GHwA@ec2-54-217-202-108.eu-west-1.compute.amazonaws.com:5432/dau4v1agvs3tmr";

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(stringURI);

        LOG.info(dbUri);
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()+"?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

        return DriverManager.getConnection(dbUrl, username, password);
    }

    Connection connection;

    public void setConnection(Connection connection){
        this.connection = connection;
    }

	public static void main(String[] args) {

        Connection connection = null;
        HomeBrokerCrawler homeBrokerCrawler = new HomeBrokerCrawler();
        try {
            connection = getConnection();
            homeBrokerCrawler.setConnection(connection);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Starting");


//		homeBrokerCrawler.processPage(RENT_START_URL);

		// for testing
		homeBrokerCrawler.processPage("https://homebroker.pl/oferty/mieszkanie-rynek-wtorny-wynajem-malopolskie-krakow-stare-miasto-jana-kochanowskiego-oferta-324103.html");
	}

	private void processPage(String url) {
		LOG.info("Enter: processPage: " + url);

		if ( !hasBeenVisited(url) && isAllowedUrl(url) ) {
			Document document = null;
			try {
				// TODO: add retries and checking if downloaded data is valid (no 404 pages)
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get(url);
				String htmlContent = driver.getPageSource();

				document = Jsoup.parse(htmlContent);
			} catch ( Exception e ) {
				LOG.error(e.getMessage());
			}

			if (document != null) {
				LOG.info("Document downloaded properly: " + url);

				if ( isOfferUrl(url) ) {
					extractData(document, url);
				}

				visitedUrls.add(url);

//				waitAfterRequests();

				followHyperLinks(document);
			} else {
				LOG.info("Document null: " + url);
			}
		}

//		if ( success ) {
//			visitedUrlsCount++;
//		}
	}

	private void extractData(Document document, String url) {
		LOG.info("Enter: extractData");

		Offer offer = new Offer();

		Elements inputs = document.select(".offer-top-info input[type=hidden]");
		Elements mapDivs = document.select("#map3");

		if ( inputs.isEmpty() || mapDivs.isEmpty() ) {
			return; // no offer on this page
		}

		for ( Element input : inputs ) {
			String id = input.id();
			String value = input.val();
			HomeBrokerOfferField homeBrokerOfferField = HomeBrokerOfferField.getInstanceForId(id);
			if ( homeBrokerOfferField != null ) {
				homeBrokerOfferField.fillOfferField(offer, value);
			}
		}

		for ( Element div : mapDivs ) {
			offer.setLatitude(Double.parseDouble(div.attr("data-lat")));
			offer.setLongitude(Double.parseDouble(div.attr("data-lng")));
		}

		setOfferType(offer, document);
		saveIfNotEmpty(offer);

		LOG.info("Obtained offer: " + offer.toString());
		System.out.println(offer);
	}

	private void followHyperLinks(Document document) {
		LOG.info("Enter: followHyperLinks");
		removeHeaderAndFooter(document);
		Elements links = document.select("a[href]");
		for ( Element link : links ) {
//			System.out.println(link.attr("abs:href"));
			processPage(link.attr("abs:href"));
		}
	}

	private void removeHeaderAndFooter(Document document) {
		for ( String selector : elementsToRemove ) {
			document.select(selector).remove();
		}
	}

	private void setOfferType(Offer offer, Document document) {
		Elements offerTypeText = document.select(".offer-top-info span.blue.strong");
		for ( String key : HomeBrokerOfferConstants.OFFER_TYPES_MAP.keySet() ) {
			if ( offerTypeText.text().contains(key) ) {
				offer.setOfferType(HomeBrokerOfferConstants.OFFER_TYPES_MAP.get(key));
			}
		}
	}

	private boolean isAllowedUrl(String url) {
		if ( url.endsWith("#") || url.endsWith(".pdf") || url.endsWith(".jpg") ) {
			return false;
		}

		boolean isValidDomain = false;
		for ( String domain : allowedDomains ) {
			if ( url.contains(domain) ) {
				isValidDomain = true;
				break;
			}
		}

		return isValidDomain;
	}

	private boolean hasBeenVisited(String url) {
		return visitedUrls.contains(url);
	}

	private boolean isOfferUrl(String url) {
		for ( String key : HomeBrokerOfferConstants.OFFER_TYPES_MAP.keySet() ) {
			if ( url.contains(key) ) {
				return true;
			}
		}
		return url.contains("oferty?offer_no");
	}

    private static String insertQuery = "INSERT INTO \"OFFER\" (\"OFFER_ID\",\"TYPE\",\"CITY\",\"PRICE\",\"AREA\",\"LATITUDE\",\"LONGITUDE\") VALUES( ? , ? , ? , ? , ? , ? , ? )";

    private void saveIfNotEmpty(Offer offer) {
		boolean isEmpty = offer.getCity() == null || offer.getPrice() == 0.0 || offer.getLatitude() == 0.0 || offer.getLongitude() == 0.0;
		if ( !isEmpty ) {
            if( ( "Krakow".equals(offer.getCity())||"Kraków".equals(offer.getCity()) ) && OfferType.RENT.equals(offer.getOfferType()) ){
                LOG.info("trying to save the offer: " + offer);
                try {
                    PreparedStatement statement = connection.prepareStatement(insertQuery);
                    statement.setLong(1,offer.getOfferId());
                    statement.setString(2,offer.getType());
                    statement.setString(3,offer.getCity());
                    statement.setDouble(4,offer.getPrice());
                    statement.setDouble(5,offer.getArea());
                    statement.setDouble(6,offer.getLatitude());
                    statement.setDouble(7,offer.getLongitude());
                    statement.executeUpdate();
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //offerDAO.saveOffer(offer);
            }
		}
	}

	private void waitAfterRequests() {
		if ( visitedUrlsCount == 10 ) {
			try {
				Thread.sleep(10000);
				visitedUrlsCount = 0;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
