/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.aqi;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import weatherstation.*;
import weatherstation.aqi.datamodel.AQIData;
import weatherstation.aqi.datamodel.WeatherStation;
import weatherstation.aqi.datamodel.WeatherData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import weatherstation.datamodel.*;
import weatherstation.util.HTTPConnector;
import weatherstation.util.JSONReader;
import weatherstation.util.Properties;
import weatherstation.util.TimeInstance;

/**
 *
 * @author Alec
 */
public class AQIFeeder extends TimerTask {
    
	private JSONReader jsonReader = new JSONReader();
	
    @Override
    public void run() {
        try {
        	Properties.getInstance().refresh();
            Cache.updateDatastreams();
            Cache.updateLocation();
            Cache.updateSensor();
    		Cache.updateObservedProperties();
            getWeatherStationFromData();
            getWeatherStationDataFromData();
//            Properties.getInstance().refresh();
//            getWeatherStationFromData();
        } catch (Exception ex) {
            Logger.getLogger(AQIFeeder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getWeatherStationFromData() throws Exception {
        System.out.println("getWeatherStationFromData");
        
        //1. update cache
        String url = Properties.URL_STA + "/Things?$select=id,name&$expand=Datastreams($select=name,id)";
        Cache.getInstance().updateCache(url);
        
        //2. get EPA AQX Station
        // String str = "";
        // while(str==""){
        // HTTPConnector httpConnector = new HTTPConnector();
        // str = httpConnector.sendGet(Properties.URL_EPA_AQI_STATION);
        // }
        // System.out.println(str);

        //3. parse EPA AQX Station response
        ArrayList<WeatherStation> stationArray = jsonReader.getStation();
        for (WeatherStation station : stationArray) {
        	if(!Cache.cacheMap.containsKey("氣象站(月)-"+station.getStationName()+"-"+station.getStationID())){ // if this station has not been inserted to service
        		System.out.println("New Station: "+station.getStationName());
        		String request = station.getSTAThingEntity();
        		//4. create Things in STA
        		url = Properties.URL_STA + "/Things";
        		HTTPConnector connector = new HTTPConnector();
        		connector.sendPost(url, request);
        		Cache.updateObservedProperties();
                Cache.updateDatastreams();
        	}

        }
    }
    
        
    private void getWeatherStationDataFromData() throws Exception {
        System.out.println("getWeatherStationDataFromCSV");
        
        //1. update cache
        String url = Properties.URL_STA + "/Things?$select=id,name&$expand=Datastreams($select=name,id)";
        Cache.getInstance().updateCache(url);
        
        //2.  get EPA AQX data 撈資料
        //HTTPConnector httpConnector = new HTTPConnector();
        //String str = httpConnector.sendGet(Properties.URL_EPA_AQI_DATA);
        
        
		//3. parse EPA AQX Data response
        ArrayList<WeatherData> weatherData = jsonReader.getData();;
        for (int temp = 0; temp < weatherData.size(); temp++) {
            AQIData metro_data = new AQIData(weatherData.get(temp));
            String sitId = metro_data.getStationID();
            Cache.STACache cache = Cache.cacheMap.get("氣象站(月)-"+metro_data.getStationName()+"-"+metro_data.getStationID());
            if(cache!=null){
            	System.out.print("Process station: "+sitId+" ");
            	//4. create Observations in STA & 5. update cache
                createObservationByDatastream(metro_data, cache, "測站氣壓");
                createObservationByDatastream(metro_data, cache, "測站溫度");
                createObservationByDatastream(metro_data, cache, "相對濕度");
                createObservationByDatastream(metro_data, cache, "測站風速");
                createObservationByDatastream(metro_data, cache, "測站風向描述");
                createObservationByDatastream(metro_data, cache, "降水量");
                createObservationByDatastream(metro_data, cache, "測站日照時數");
                System.out.println("");
             }
             else{
                System.out.println("");
                System.out.println("Process station error!!!");
                System.out.println("");
             }
        }
    }
    
    private void createObservationByDatastream(AQIData aqiData, Cache.STACache cache, String datastreamName) throws IOException, ParseException{
        int datastreamID;
        long lastPheonmenonTime = cache.datastreamTimeMap.get(datastreamName);            
        datastreamID = cache.datastreamIDMap.get(datastreamName);
        LocalDateTime date = LocalDateTime.parse(aqiData.getDataTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        long phenomenonTime = date.toEpochSecond(ZoneOffset.UTC);
        
        if(phenomenonTime>lastPheonmenonTime){ //if is a new observation, insert to the service
            System.out.print(datastreamID+",");
            
            String request = aqiData.getSTAObservationEntity(datastreamName);
            
            if(request!=null){
                String url = Properties.URL_STA + "/Datastreams(" + datastreamID + ")/Observations";
                HTTPConnector connector = new HTTPConnector();
                connector.sendPost(url, request);
                
                // 5. update cache
                cache.datastreamTimeMap.put(datastreamName, phenomenonTime);
            }
        }
    }
    
}
