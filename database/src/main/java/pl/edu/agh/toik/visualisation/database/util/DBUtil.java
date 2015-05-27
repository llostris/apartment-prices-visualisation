package pl.edu.agh.toik.visualisation.database.util;

import javax.sql.DataSource;

/**
 * Created by Puszek_SE on 2015-05-27.
 */
public class DBUtil {
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void initialize(){
        //DoNothing
    }
}
