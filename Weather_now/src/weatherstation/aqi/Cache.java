/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.aqi;

import java.util.HashMap;

import weatherstation.datamodel.Datastream;
import weatherstation.datamodel.Location;
import weatherstation.datamodel.ObservedProperty;
import weatherstation.datamodel.Sensor;
import weatherstation.util.HTTPConnector;
import weatherstation.util.Properties;
import weatherstation.util.TimeInstance;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author alec
 */
public class Cache {
    private static Cache instance = new Cache();
    public static HashMap<String, STACache> cacheMap = new HashMap<String, STACache>(); //thingName, STACache
    
    public Cache(){
        
    }
    public static Cache getInstance() {
        return instance;
    }
    
    class STACache{
        public String thingName = null;
        public int thingID = Integer.MAX_VALUE;
        public HashMap<String, Integer> datastreamIDMap = new HashMap<String, Integer>();
        public HashMap<String, Long> datastreamTimeMap = new HashMap<String, Long>();
        
        public STACache(String thingName, int thingID, HashMap<String, Integer> datastreamIDMap, HashMap<String, Long> datastreamTimeMap){
            this.thingName = thingName;
            this.thingID = thingID;
            this.datastreamIDMap = datastreamIDMap;
            this.datastreamTimeMap = datastreamTimeMap;
        }
    }
    
	public static void updateSensor() throws Exception {
        
        HTTPConnector connector = new HTTPConnector();
        String sta_response = connector.sendGetJSON(Properties.URL_STA+"/Sensors?$select=@iot.id,name");
        JSONObject obj = new JSONObject(sta_response);
        
        if(obj.has("value")){
            JSONArray sensorsArray = obj.getJSONArray("value");
            for(int i=0; i<sensorsArray.length(); i++){
                JSONObject sensor = (JSONObject) sensorsArray.get(i);
                int id = sensor.getInt("@iot.id");
                String name = sensor.getString("name");
                if(!Sensor.sensors.containsKey(name)) {
                	Sensor.sensors.put(name, id);
                }
            }
        }
        while(obj.has("@iot.nextLink")) {
        	String nextURL = obj.getString("@iot.nextLink")+"&$select=@iot.id,name";
        	sta_response = connector.sendGetJSON(nextURL);
        	obj = new JSONObject(sta_response);
        	if(obj.has("value")){
        		JSONArray sensorsArray = obj.getJSONArray("value");
                for(int i=0; i<sensorsArray.length(); i++){
                    JSONObject sensor = (JSONObject) sensorsArray.get(i);
                    int id = sensor.getInt("@iot.id");
                    String name = sensor.getString("name");
                    if(!Sensor.sensors.containsKey(name)) {
                    	Sensor.sensors.put(name, id);
                    }
                }
            }
        }
	}
    
    public void updateCache(String url) throws Exception{
        System.out.println("updateCache");
        
        HTTPConnector connector = new HTTPConnector();
        String sta_response = connector.sendGetJSON(url);
        JSONObject obj = new JSONObject(sta_response);
        
        if(obj.has("value")){
            JSONArray array_device = obj.getJSONArray("value");
            for(int index_device=0; index_device<array_device.length(); index_device++){
                JSONObject obj_device = (JSONObject) array_device.get(index_device);
                int thingID = obj_device.getInt("@iot.id");
                String thingName = obj_device.getString("name");
                
                HashMap<String, Integer> datastreamIDMap = new HashMap<String, Integer>();
                HashMap<String, Long> datastreamTimeMap = new HashMap<String, Long>();
                
                JSONArray array_datastream = obj_device.getJSONArray("Datastreams");
                for(int index_datastream=0; index_datastream<array_datastream.length(); index_datastream++){
                    JSONObject obj_datastream = (JSONObject) array_datastream.get(index_datastream);
                    int datastreamID = obj_datastream.getInt("@iot.id");
                    String datastreamName = obj_datastream.getString("name");
                    datastreamIDMap.put(datastreamName, datastreamID);
                    
                    long lastPheonmenonTime = getLastPhenomenonTimeOfDatastream(datastreamID);
                    datastreamTimeMap.put(datastreamName, lastPheonmenonTime);
                }
                
                STACache cache = new STACache(thingName, thingID, datastreamIDMap, datastreamTimeMap);
                
                cacheMap.put(thingName, cache);
            }
        }
        
        if(obj.has("@iot.nextLink")){
            String nextLink = obj.getString("@iot.nextLink");
            updateCache(nextLink);
        }
    }
    
	public static void updateObservedProperties() throws Exception {
		System.out.println("updateObservedProperties");
        
        HTTPConnector connector = new HTTPConnector();
        String sta_response = connector.sendGetJSON(Properties.URL_STA+"/ObservedProperties?$select=@iot.id,name");
        JSONObject obj = new JSONObject(sta_response);
        
        if(obj.has("value")){
            JSONArray observedPropertiesArray = obj.getJSONArray("value");
            for(int i=0; i<observedPropertiesArray.length(); i++){
                JSONObject observedProperty = (JSONObject) observedPropertiesArray.get(i);
                int id = observedProperty.getInt("@iot.id");
                String name = observedProperty.getString("name");
                if(!ObservedProperty.observedProperties.containsKey(name)) {
                	ObservedProperty.observedProperties.put(name, id);
                }
            }
        }
        while(obj.has("@iot.nextLink")) {
        	String nextURL = obj.getString("@iot.nextLink")+"&$select=@iot.id,name";
        	sta_response = connector.sendGetJSON(nextURL);
        	obj = new JSONObject(sta_response);
        	if(obj.has("value")){
        		JSONArray observedPropertiesArray = obj.getJSONArray("value");
                for(int i=0; i<observedPropertiesArray.length(); i++){
                    JSONObject observedProperty = (JSONObject) observedPropertiesArray.get(i);
                    int id = observedProperty.getInt("@iot.id");
                    String name = observedProperty.getString("name");
                    if(!ObservedProperty.observedProperties.containsKey(name)) {
                    	ObservedProperty.observedProperties.put(name, id);
                    }
                }
            }
        }
	}
	public static void updateLocation() throws Exception {
		HTTPConnector connector = new HTTPConnector();
        String sta_response = connector.sendGetJSON(Properties.URL_STA+"/Locations?$select=@iot.id,name");
        JSONObject obj = new JSONObject(sta_response);
        
        if(obj.has("value")){
            JSONArray locationsArray = obj.getJSONArray("value");
            for(int i=0; i<locationsArray.length(); i++){
                JSONObject location = (JSONObject) locationsArray.get(i);
                int id = location.getInt("@iot.id");
                String name = location.getString("name");
                if(!Location.locations.containsKey(name)) {
                	Location.locations.put(name, id);
                }
            }
        }
        while(obj.has("@iot.nextLink")) {
        	String nextURL = obj.getString("@iot.nextLink")+"&$select=@iot.id,name";
        	sta_response = connector.sendGetJSON(nextURL);
        	obj = new JSONObject(sta_response);
        	if(obj.has("value")){
        		JSONArray locationsArray = obj.getJSONArray("value");
                for(int i=0; i<locationsArray.length(); i++){
                    JSONObject location = (JSONObject) locationsArray.get(i);
                    int id = location.getInt("@iot.id");
                    String name = location.getString("name");
                    if(!Location.locations.containsKey(name)) {
                    	Location.locations.put(name, id);
                    }
                }
            }
        }	
	}
	public static void updateDatastreams() throws Exception {
		HTTPConnector connector = new HTTPConnector();
        String sta_response = connector.sendGetJSON(Properties.URL_STA+"/Datastreams?$select=@iot.id,description");
        JSONObject obj = new JSONObject(sta_response);
        
        if(obj.has("value")){
            JSONArray datastreamsArray = obj.getJSONArray("value");
            for(int i=0; i<datastreamsArray.length(); i++){
                JSONObject datastream = (JSONObject) datastreamsArray.get(i);
                int id = datastream.getInt("@iot.id");
                String description = datastream.getString("description");
                if(!Datastream.datastreams.containsKey(description)) {
                	Datastream.datastreams.put(description, id);
                }
            }
        }
        while(obj.has("@iot.nextLink")) {
        	String nextURL = obj.getString("@iot.nextLink")+"&$select=@iot.id,description";
        	sta_response = connector.sendGetJSON(nextURL);
        	obj = new JSONObject(sta_response);
        	if(obj.has("value")){
        		JSONArray datastreamsArray = obj.getJSONArray("value");
                for(int i=0; i<datastreamsArray.length(); i++){
                	JSONObject datastream = (JSONObject) datastreamsArray.get(i);
                    int id = datastream.getInt("@iot.id");
                    String description = datastream.getString("description");
                    if(!Datastream.datastreams.containsKey(description)) {
                    	Datastream.datastreams.put(description, id);
                    }
                }
            }
        }	
	}	
    
    private long getLastPhenomenonTimeOfDatastream(int datastreamID) throws Exception{
        long lastPhenomenonTime = Long.MIN_VALUE;
        
        String url_observation_time = Properties.URL_STA+"/Datastreams("+datastreamID+")/Observations?$orderby=phenomenonTime%20desc&$top=1&$select=phenomenonTime";
        HTTPConnector connector = new HTTPConnector();
        String sta_response = connector.sendGetJSON(url_observation_time);
        JSONObject obj = new JSONObject(sta_response);
        
        JSONArray array_device = obj.getJSONArray("value");
        
        if(array_device.length()>0){
            JSONObject obj_observation = (JSONObject) array_device.get(0);
            String phenomenonTimeString = obj_observation.getString("phenomenonTime");

            TimeInstance timeInstance = new TimeInstance(phenomenonTimeString);
            lastPhenomenonTime = timeInstance.getCalendar().getTimeInMillis();
        }
        
        return lastPhenomenonTime;
    }
    
}
