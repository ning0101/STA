/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author alec
 */
public class Sensor{
    public String name = null;
    public String description = null; 
    public String encodingType = null;
    public JSONArray keyword = new JSONArray();
    public JSONArray Characteristics = new JSONArray();
    public JSONArray Classification = new JSONArray();
    public JSONArray Capabilities = new JSONArray();
    public JSONArray identifier = new JSONArray();
    public int id = 0;
	public static HashMap<String, Integer> sensors = new HashMap<String, Integer>();
	public ArrayList<String> exist = new ArrayList<String>();
//	public T metadata = null;



    public Sensor(String name, String description, String encodingType){
        this.name = name;
        this.description = description;
        this.encodingType = encodingType;
    }
    
    public Sensor(int id) {
    	this.id = id;
    }
    

	public JSONObject getJSONObject(){
        JSONObject Sensor = new JSONObject();
        
        if(id>0) {
        	Sensor.put("@iot.id", id);
       	
        }
        else {
        Sensor.put("name", name);
        Sensor.put("description", description);
        Sensor.put("encodingType", encodingType);
        JSONObject metadataObj = new JSONObject();
        JSONObject sensorMLObject = new JSONObject();
        sensorMLObject.put("keyword", this.keyword);
        sensorMLObject.put("Characteristics", this.Characteristics);
        sensorMLObject.put("Classification", this.Classification);
        sensorMLObject.put("Capabilities", this.Capabilities);
        sensorMLObject.put("identifier", this.identifier);
        metadataObj.put("SensorML", sensorMLObject);
        Sensor.put("metadata", metadataObj);
        }
        return Sensor;
    }
    
    public void addToJSONArray(String target, String value) {
    	switch(target){
    		case "keyword":
    			this.keyword.put(value);
    			break;
    		case "Characteristics":
    			this.Characteristics.put(value);
    			break;
    		case "Classification":
    			this.Classification.put(value);
    			break;
    		case "Capabilities":
    			this.Capabilities.put(value);
    			break;
    		case "identifier":
    			this.identifier.put(value);
    			break;
    		default:
    			break;
    	}
    }
    
    public String getJSONString(){
        return getJSONObject().toString();
    }
}
