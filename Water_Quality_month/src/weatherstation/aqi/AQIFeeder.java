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
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import weatherstation.*;
import weatherstation.aqi.datamodel.AQIData;
import weatherstation.aqi.datamodel.AQIStation;
import weatherstation.aqi.datamodel.WeatherStation;
import weatherstation.aqi.datamodel.metro_20181102;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import weatherstation.datamodel.*;
import weatherstation.util.CSVReader_data;
import weatherstation.util.CSVReader_weatherstation;
import weatherstation.util.HTTPConnector;
import weatherstation.util.Properties;
import weatherstation.util.TimeInstance;

/**
 *
 * @author Alec
 */
public class AQIFeeder extends TimerTask {
    
	private CSVReader_weatherstation csv_station = new CSVReader_weatherstation();
	private CSVReader_data csv_data = new CSVReader_data();
	
    @Override
    public void run() {
        try {
            Properties.getInstance().refresh();
            
            getWeatherStationFromCSV();
            getWeatherStationDataFromCSV();
        } catch (Exception ex) {
            Logger.getLogger(AQIFeeder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getWeatherStationFromCSV() throws Exception {
        System.out.println("getWeatherStationFromCSV");
        
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
        ArrayList<WeatherStation> weatherStations = csv_station.readStationCSV();
        for (int temp = 0; temp < weatherStations.size(); temp++) {
               AQIStation station = new AQIStation(weatherStations.get(temp));
               if(!Cache.cacheMap.containsKey("局屬氣象站-"+station.station_name)){ // if this station has not been inserted to service
                        System.out.println("New Station: "+station.station_name);

                        String request = station.getSTAThingEntity();

                        //4. create Things in STA
                        url = Properties.URL_STA + "/Things";
                        HTTPConnector connector = new HTTPConnector();
                        connector.sendPost(url, request);
                    }

                }
            }
    
        
    private void getWeatherStationDataFromCSV() throws Exception {
        System.out.println("getWeatherStationDataFromCSV");
        
        //1. update cache
        String url = Properties.URL_STA + "/Things?$select=id,name&$expand=Datastreams($select=name,id)";
        Cache.getInstance().updateCache(url);
        
        //2.  get EPA AQX data 撈資料
        //HTTPConnector httpConnector = new HTTPConnector();
        //String str = httpConnector.sendGet(Properties.URL_EPA_AQI_DATA);
        
        
		//3. parse EPA AQX Data response
        ArrayList<metro_20181102> metro_20181102 = csv_data.readDataCSV();
        for (int temp = 0; temp < metro_20181102.size(); temp++) {
            AQIData metro_data = new AQIData(metro_20181102.get(temp));
            String station_id = metro_data.getStation_id();
            Cache.STACache cache = Cache.cacheMap.get("局屬氣象站-"+station_id);
            if(cache!=null){
            	System.out.print("Process station: "+station_id+" ");
            	//4. create Observations in STA & 5. update cache
                createObservationByDatastream(metro_data, cache, "ELEV");
                createObservationByDatastream(metro_data, cache, "WDIR");
                createObservationByDatastream(metro_data, cache, "WDSD");
                createObservationByDatastream(metro_data, cache, "TEMP");
                createObservationByDatastream(metro_data, cache, "HUMD");
                createObservationByDatastream(metro_data, cache, "PRES");
                createObservationByDatastream(metro_data, cache, "R24");
                createObservationByDatastream(metro_data, cache, "H_FX");
                createObservationByDatastream(metro_data, cache, "H_XD");
                createObservationByDatastream(metro_data, cache, "H_FXT");
                createObservationByDatastream(metro_data, cache, "H_F10");
                createObservationByDatastream(metro_data, cache, "H_10D");
                createObservationByDatastream(metro_data, cache, "H_F10T");
                createObservationByDatastream(metro_data, cache, "H_UVI");
                createObservationByDatastream(metro_data, cache, "D_TX");
                createObservationByDatastream(metro_data, cache, "D_TXT");
                createObservationByDatastream(metro_data, cache, "D_TN");
                createObservationByDatastream(metro_data, cache, "D_TNT");
                createObservationByDatastream(metro_data, cache, "D_TS");
                createObservationByDatastream(metro_data, cache, "H_VIS");
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
        long lastPheonmenonTime;
        if(datastreamName.equalsIgnoreCase("R24")) {
            lastPheonmenonTime = cache.datastreamTimeMap.get("24R");
            datastreamID = cache.datastreamIDMap.get("24R");
        }else {
            lastPheonmenonTime = cache.datastreamTimeMap.get(datastreamName);            
            datastreamID = cache.datastreamIDMap.get(datastreamName);
        }
        TimeInstance timeInstance = new TimeInstance(aqiData.getObsTime());
        long phenomenonTime = timeInstance.getCalendar().getTimeInMillis();
        
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
