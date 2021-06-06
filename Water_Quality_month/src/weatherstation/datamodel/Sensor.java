/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.datamodel;

import org.json.JSONObject;

/**
 *
 * @author alec
 */
public class Sensor <T>{
    public String name = null;
    public String description = null; 
    public String encodingType = null;
    public T metadata = null;

    public Sensor(String name, String description, String encodingType, T metadata){
        this.name = name;
        this.description = description;
        this.encodingType = encodingType;
        this.metadata = metadata;
    }
    
    public JSONObject getJSONObject(){
        JSONObject Sensor = new JSONObject();
        
        Sensor.put("name", name);
        Sensor.put("description", description);
        Sensor.put("encodingType", encodingType);
        JSONObject metadataObj = new JSONObject();
        metadataObj.put("metadata", metadata.toString());
        Sensor.put("metadata", metadataObj);
        
        return Sensor;
    }
    
    public String getJSONString(){
        return getJSONObject().toString();
    }
}
