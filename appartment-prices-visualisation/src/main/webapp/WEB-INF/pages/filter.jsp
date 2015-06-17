<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="filters" class="col-md-9">
    <form id="filterForm">
        <div class="row form-inline">
            <fieldset>
                <div class="col-md-12">
                    <label for="mapFilterAreaMin">Price</label>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="mapFilterPriceMinAddon">From</span>
                    <input type="text" required="" class="form-control" placeholder="0" id="mapFilterPriceMin" data-param="price-min">
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="mapFilterPriceMaxAddon">To</span>
                    <input type="text" required="" class="form-control" placeholder="0" id="mmapFilterPriceMax" data-param="price-max">
                </div>
            </fieldset>
        </div>
        <div class="row form-inline">
            <fieldset>
                <div class="col-md-12">
                    <label for="mapFilterAreaMin">Area</label>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="mapFilterAreaMinAddon">From</span>
                    <input type="text" required="" class="form-control" placeholder="0" id="mapFilterAreaMin" data-param="area-min">
                </div>
                <div class="input-group">
                    <span class="input-group-addon" id="mapFilterAreaMaxAddon">To</span>
                    <input type="text" required="" class="form-control" placeholder="0" id="mapFilterAreaMax" data-param="area-max">
                </div>
            </fieldset>
        </div>

        <div class="row form-inline">
            <fieldset>
                <div class="col-md-12">
                    <label for="mapFilterType">Type</label>
                </div>
                <div class="input-group">
                    <select id="mapFilterType" data-param="type" class="form-control">
                        <!-- TODO: get from model -->
                        <option></option>
                        <option>Mieszkanie</option>
                        <option>Dom</option>
                        <option>Lokal użytkowy</option>
                    </select>
                </div>
            </fieldset>
        </div>
        <div class="row form-inline">
            <fieldset>
                <div class="col-md-12">
                    <label for="mapFilterDistrict">District</label>
                </div>
                <div class="input-group">
                    <select id="mapFilterDistrict" data-param="district" class="form-control">
                        <c:forEach items="${districts}" var="district">
                            <option>${district}</option>
                        </c:forEach>
                    </select>
                </div>
            </fieldset>
        </div>
    </form>
    <div class="row">
        <button type="button" class="btn btn-primary" id="filterButton">Filter</button>
    </div>
</div>