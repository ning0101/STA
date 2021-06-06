/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.aqi.datamodel;

import java.text.ParseException;
import java.util.ArrayList;

import weatherstation.datamodel.Datastream;
import weatherstation.datamodel.Location;
import weatherstation.datamodel.ObservedProperty;
import weatherstation.datamodel.Sensor;
import weatherstation.datamodel.UnitOfMeasurement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;


/**
 *
 * @author alec
 */
public class AQIStation {
	private String stationId = null;
	private String locationName = null;
	private double lat_wgs84 = Double.MAX_VALUE;
	private double lon_wgs84 = Double.MAX_VALUE;
	private String obsTime = null;
	private String CITY = null;
	private String CITY_SN = null;
	private String TOWN = null;
	private String TOWN_SN = null;
	private String elementName = null;



	// weather_station_metro.csv
	public AQIStation(WeatherStation weatherStation,WeatherData weather_data) {
		this.elementName = weatherStation.getElementName();
		this.stationId = weatherStation.getStationId();
		this.locationName = weatherStation.getLocationName();
		this.CITY = weatherStation.getCITY();
		this.CITY_SN = weatherStation.getCITY_SN();
		this.TOWN = weatherStation.getTOWN();
		this.TOWN_SN = weatherStation.getTOWN_SN();
		this.lon_wgs84 = weatherStation.getLon_wgs84();
		this.lat_wgs84 = weatherStation.getLat_wgs84();
		this.obsTime = weather_data.getObsTime();
	}
//    public AQIStation(String jsonString) throws JSONException, ParseException{
//        JSONObject obj = new JSONObject(jsonString);
//        this.parseJSONObject(obj);
//    }
//    public AQIStation(JSONObject obj) throws JSONException, ParseException{
//        this.parseJSONObject(obj);
//    }


}
