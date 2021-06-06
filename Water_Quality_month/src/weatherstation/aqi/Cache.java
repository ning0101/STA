/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.aqi;

import java.util.HashMap;
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
