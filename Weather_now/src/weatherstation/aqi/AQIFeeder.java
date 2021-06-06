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
        ArrayList<WeatherStation> stationArray = jsonReader.getStation();
        HashMap<String, ArrayList<String>> stationDatastreamMap = new HashMap<String, ArrayList<String>>();
        for (int temp = 0; temp < stationArray.size(); temp++) {
        	WeatherStation station = stationArray.get(temp);
            if(stationDatastreamMap.containsKey("氣象站(現在)-"+station.getLocationName()+"-"+station.getStationId())) {
            	ArrayList<String> datastreamList = stationDatastreamMap.get("氣象站(現在)-"+station.getLocationName()+"-"+station.getStationId());
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
            		case "H_24R":
                		datastreamList.add("日累積雨量");
                		break;
            		case "H_FX":
                		datastreamList.add("小時瞬間最大陣風風速");
                		break;
            		case "H_XD":
                		datastreamList.add("小時瞬間最大陣風風向 ");
                		break;
            		case "H_F10":
            			datastreamList.add("本時最大10分鐘平均風速");
            			break;
            		case "H_10D":
            			datastreamList.add("本時最大10分鐘平均風向");
            			break;
            		case "H_UVI":
            			datastreamList.add("小時紫外線指數");
            			break;
            		case "D_TX":
                		datastreamList.add("本日最高溫度");
                		break;
            		case "D_TN":
                    	datastreamList.add("本日最低溫度");
                    	break;
            		case "VIS":
                    	datastreamList.add("本時整點盛行能見度");
                    	break;
            		case "Weather":
                    	datastreamList.add("本時整點天氣現樣描述");
                    	break;
            		}
            		stationDatastreamMap.put("氣象站(現在)-"+station.getLocationName()+"-"+station.getStationId(), datastreamList);
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
        		case "H_24R":
            		datastreamList.add("日累積雨量");
            		break;
        		case "H_FX":
            		datastreamList.add("小時瞬間最大陣風風速");
            		break;
        		case "H_XD":
            		datastreamList.add("小時瞬間最大陣風風向 ");
            		break;
        		case "H_F10":
        			datastreamList.add("本時最大10分鐘平均風速");
        			break;
        		case "H_10D":
        			datastreamList.add("本時最大10分鐘平均風向");
        			break;
        		case "H_UVI":
        			datastreamList.add("小時紫外線指數");
        			break;
        		case "D_TX":
            		datastreamList.add("本日最高溫度");
            		break;
        		case "D_TN":
                	datastreamList.add("本日最低溫度");
                	break;
        		case "VIS":
                	datastreamList.add("本時整點盛行能見度");
                	break;
        		case "Weather":
                	datastreamList.add("本時整點天氣現樣描述");
                	break;
            		
        		}
        		stationDatastreamMap.put("氣象站(現在)-"+station.getLocationName()+"-"+station.getStationId(), datastreamList);
            }
         }
        

        //3. parse EPA AQX Station response
        
//        ArrayList<WeatherStation> stationArray = jsonReader.getStation();
        for (WeatherStation station : stationArray) {
        	if(!Cache.cacheMap.containsKey("氣象站(現在)-"+station.getLocationName()+"-"+station.getStationId())){ // if this station has not been inserted to service
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
            String sitId = metro_data.getStationId();
            Cache.STACache cache = Cache.cacheMap.get("氣象站(現在)-"+metro_data.getLocationName()+"-"+metro_data.getStationId());
            if(cache!=null){
            	System.out.print("Process station: "+sitId+" ");
            	//4. create Observations in STA & 5. update cache
//                createObservationByDatastream(metro_data, cache, "ELEV");
                createObservationByDatastream(metro_data, cache, "WDIR");
                createObservationByDatastream(metro_data, cache, "WDSD");
                createObservationByDatastream(metro_data, cache, "TEMP");
                createObservationByDatastream(metro_data, cache, "HUMD");
                createObservationByDatastream(metro_data, cache, "PRES");
                createObservationByDatastream(metro_data, cache, "H_24R");
                createObservationByDatastream(metro_data, cache, "H_FX");
                createObservationByDatastream(metro_data, cache, "H_XD");
                createObservationByDatastream(metro_data, cache, "H_F10");
                createObservationByDatastream(metro_data, cache, "H_10D");
                createObservationByDatastream(metro_data, cache, "H_UVI");
//                createObservationByDatastream(metro_data, cache, "H_FXT");
                createObservationByDatastream(metro_data, cache, "D_TX");
//                createObservationByDatastream(metro_data, cache, "D_TXT");
                createObservationByDatastream(metro_data, cache, "D_TN");
//                createObservationByDatastream(metro_data, cache, "D_TNT");
                createObservationByDatastream(metro_data, cache, "VIS");
                createObservationByDatastream(metro_data, cache, "Weather");
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
        long lastPheonmenonTime = 0;;
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
        	case "H_F10":
        		lastPheonmenonTime = cache.datastreamTimeMap.get("本時最大10分鐘平均風速");
        		datastreamID = cache.datastreamIDMap.get("本時最大10分鐘平均風速");
                break;
        	case "H_10D":
        		lastPheonmenonTime = cache.datastreamTimeMap.get("本時最大10分鐘平均風向");
        		datastreamID = cache.datastreamIDMap.get("本時最大10分鐘平均風向");
        	case "H_UVI":
        		lastPheonmenonTime = cache.datastreamTimeMap.get("小時紫外線指數");
        		datastreamID = cache.datastreamIDMap.get("小時紫外線指數");
                break;
        	case "D_TX":
        		lastPheonmenonTime = cache.datastreamTimeMap.get("本日最高溫度");
        		datastreamID = cache.datastreamIDMap.get("本日最高溫度");
                break;  
        	case "D_TN":
        		lastPheonmenonTime = cache.datastreamTimeMap.get("本日最低溫度");
        		datastreamID = cache.datastreamIDMap.get("本日最低溫度");
                break;
        	case "VIS":
        		lastPheonmenonTime = cache.datastreamTimeMap.get("本時整點盛行能見度");
        		datastreamID = cache.datastreamIDMap.get("本時整點盛行能見度");
                break;
        	case "Weather":
        		lastPheonmenonTime = cache.datastreamTimeMap.get("本時整點天氣現樣描述");
        		datastreamID = cache.datastreamIDMap.get("本時整點天氣現樣描述");
                break;
        		
        }
//        long phenomenonTime = aqiData.getFormattedDateTime().toEpochSecond(ZoneOffset.UTC);
//	      int datastreamID;
//	      long lastPheonmenonTime = cache.datastreamTimeMap.get(datastreamName);            
//	      datastreamID = cache.datastreamIDMap.get(datastreamName);
	      LocalDateTime date = LocalDateTime.parse(aqiData.getObsTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
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
                
                
//    private void createObservationByDatastream(AQIData aqiData, Cache.STACache cache, String datastreamName) throws IOException, ParseException{
//        int datastreamID;
//        long lastPheonmenonTime = cache.datastreamTimeMap.get(datastreamName);            
//        datastreamID = cache.datastreamIDMap.get(datastreamName);
//        LocalDateTime date = LocalDateTime.parse(aqiData.getObsTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        long phenomenonTime = date.toEpochSecond(ZoneOffset.UTC);
//        
//        if(phenomenonTime>lastPheonmenonTime){ //if is a new observation, insert to the service
//            System.out.print(datastreamID+",");
//            
//            String request = aqiData.getSTAObservationEntity(datastreamName);
//            
//            if(request!=null){
//                String url = Properties.URL_STA + "/Datastreams(" + datastreamID + ")/Observations";
//                HTTPConnector connector = new HTTPConnector();
//                connector.sendPost(url, request);
//                
//                // 5. update cache
//                cache.datastreamTimeMap.put(datastreamName, phenomenonTime);
            }
        }
    }
    
}
