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
import java.util.HashMap;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import weatherstation.*;
import weatherstation.aqi.datamodel.AQIData;
import weatherstation.aqi.datamodel.AQIStation;
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
            getWeatherStationFromData();
            getWeatherStationDataFromData();
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
        HashMap<String, ArrayList<String>> stationDatastreamMap = new HashMap<String, ArrayList<String>>();
        for (int temp = 0; temp < stationArray.size(); temp++) {
        	WeatherStation station = stationArray.get(temp);
            if(stationDatastreamMap.containsKey("自動氣象站-"+station.getLocationName()+"-"+station.getStationId())) {
            	ArrayList<String> datastreamList = stationDatastreamMap.get("自動氣象站-"+station.getLocationName()+"-"+station.getStationId());
            	if(!datastreamList.contains(station.getElementName())) {
            		switch(station.getElementName()) {
            		case "WDIR":
                		datastreamList.add("平均風風向");
                		break;
            		case "WDSD":
                		datastreamList.add("平均風風速");
                		break;
            		case "TEMP":
                		datastreamList.add("氣溫");
                		break;
            		case "HUMD":
                		datastreamList.add("相對濕度");
                		break;
            		case "PRES":
                		datastreamList.add("氣壓");
                		break;
            		case "HOUR_24":
                		datastreamList.add("日累積雨量");
                		break;
            		case "H_FX":
                		datastreamList.add("小時瞬間最大陣風風速");
                		break;
            		case "H_XD":
                		datastreamList.add("小時瞬間最大陣風風向 ");
                		break;
            		case "D_TX":
                		datastreamList.add("本日最高溫度");
                		break;
            		case "D_TN":
                    	datastreamList.add("本日最低溫度");
                    	break;
            		}
            		stationDatastreamMap.put("自動氣象站-"+station.getLocationName()+"-"+station.getStationId(), datastreamList);
            	}
            }else {
            	ArrayList<String> datastreamList = new ArrayList<String>();
            	switch(station.getElementName()) {
        		case "WDIR":
            		datastreamList.add("平均風風向");
            		break;
        		case "WDSD":
            		datastreamList.add("平均風風速");
            		break;
        		case "TEMP":
            		datastreamList.add("氣溫");
            		break;
        		case "HUMD":
            		datastreamList.add("相對濕度");
            		break;
        		case "PRES":
            		datastreamList.add("氣壓");
            		break;
        		case "HOUR_24":
            		datastreamList.add("日累積雨量");
            		break;
        		case "H_FX":
            		datastreamList.add("小時瞬間最大陣風風速");
            		break;
        		case "H_XD":
            		datastreamList.add("小時瞬間最大陣風風向 ");
            		break;
        		case "D_TX":
            		datastreamList.add("本日最高溫度");
            		break;
        		case "D_TN":
                	datastreamList.add("本日最低溫度");
                	break;
            		
        		}
        		stationDatastreamMap.put("自動氣象站-"+station.getLocationName()+"-"+station.getStationId(), datastreamList);
            }
         }
        for (WeatherStation station : stationArray) {
        	if(!Cache.cacheMap.containsKey("自動氣象站-"+station.getLocationName()+station.getStationId())){ // if this station has not been inserted to service
        		System.out.println("New Station: "+station.getLocationName());
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
        ArrayList<WeatherData> weatherData = new ArrayList<WeatherData>();
        weatherData = JSONReader.getData();
        for (int temp = 0; temp < weatherData.size(); temp++) {
            AQIData metro_data = new AQIData(weatherData.get(temp));
            Cache.STACache cache = Cache.cacheMap.get("自動氣象站-"+metro_data.getLocationName() +"-"+ metro_data.getStationId());
            if(cache!=null){
            	System.out.print("Process station: "+metro_data.getStationId()+" ");
            	//4. create Observations in STA & 5. update cache
                createObservationByDatastream(metro_data, cache, "WDIR");
                createObservationByDatastream(metro_data, cache, "WDSD");
                createObservationByDatastream(metro_data, cache, "TEMP");
                createObservationByDatastream(metro_data, cache, "HUMD");
                createObservationByDatastream(metro_data, cache, "PRES");
                createObservationByDatastream(metro_data, cache, "H_24R");
                createObservationByDatastream(metro_data, cache, "H_FX");
                createObservationByDatastream(metro_data, cache, "H_XD");
//                createObservationByDatastream(metro_data, cache, "H_FXT");
                createObservationByDatastream(metro_data, cache, "D_TX");
//                createObservationByDatastream(metro_data, cache, "D_TXT");
                createObservationByDatastream(metro_data, cache, "D_TN");
//                createObservationByDatastream(metro_data, cache, "D_TNT");
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
        int datastreamID = 0;
        long lastPheonmenonTime = 0;
//        long lastPheonmenonTime = cache.datastreamTimeMap.get(datastreamName);  
        switch(datastreamName) {
    	case "WDIR":
            lastPheonmenonTime = cache.datastreamTimeMap.get("平均風風向");           
            datastreamID = cache.datastreamIDMap.get("平均風風向");
            break;
    	case "WDSD":
            lastPheonmenonTime = cache.datastreamTimeMap.get("平均風風速");           
            datastreamID = cache.datastreamIDMap.get("平均風風速");
            break;
    	case "TEMP":
    		lastPheonmenonTime = cache.datastreamTimeMap.get("氣溫");
    		datastreamID = cache.datastreamIDMap.get("氣溫");
            break;
    	case "HUMD":
    		lastPheonmenonTime = cache.datastreamTimeMap.get("相對濕度");
    		datastreamID = cache.datastreamIDMap.get("相對濕度");
            break;
    	case "PRES":
    		lastPheonmenonTime = cache.datastreamTimeMap.get("氣壓");
    		datastreamID = cache.datastreamIDMap.get("氣壓");
            break;
    	case "H_24R":
    		lastPheonmenonTime = cache.datastreamTimeMap.get("日累積雨量");
    		datastreamID = cache.datastreamIDMap.get("日累積雨量");
            break;
    	case "H_FX":
    		lastPheonmenonTime = cache.datastreamTimeMap.get("小時瞬間最大陣風風速");
    		datastreamID = cache.datastreamIDMap.get("小時瞬間最大陣風風速");
            break;
    	case "H_XD":
    		lastPheonmenonTime = cache.datastreamTimeMap.get("小時瞬間最大陣風風向");
    		datastreamID = cache.datastreamIDMap.get("小時瞬間最大陣風風向");
            break;
    	case "D_TX":
    		lastPheonmenonTime = cache.datastreamTimeMap.get("本日最高溫度");
    		datastreamID = cache.datastreamIDMap.get("本日最高溫度");
            break;    
    	case "D_TN":
    		lastPheonmenonTime = cache.datastreamTimeMap.get("本日最低溫度");
    		datastreamID = cache.datastreamIDMap.get("本日最低溫度");
            break; 
        }
//        datastreamID = cache.datastreamIDMap.get(datastreamName);
        LocalDateTime date = LocalDateTime.parse(aqiData.getObsTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        long phenomenonTime = date.toEpochSecond(ZoneOffset.UTC);
//        long phenomenonTime = AQIData.getObsTime().toEpochSecond(ZoneOffset.UTC);
 
  
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
