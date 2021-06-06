/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.datamodel;

import java.util.HashMap;

import org.json.JSONObject;

/**
 *
 * @author alec
 */
public class ObservedProperty {
	public static HashMap<String, Integer> observedProperties = new HashMap<String, Integer>();
    public String name = null;
    public String definition = null;
    public String description = null;
    public int id = 0;

    public ObservedProperty(String name, String description, String definition){
        this.name = name;
        this.description = description;
        this.definition = definition;
    }
    public ObservedProperty(int id){
        this.id = id;
    }
    
    public JSONObject getJSONObject(){
        JSONObject ObservedProperty = new JSONObject();
        if(this.id==0) {
	        ObservedProperty.put("name", name);
	        ObservedProperty.put("definition", definition);
	        ObservedProperty.put("description", description);
        }else {
        	ObservedProperty.put("@iot.id", this.id);
        }
        
        return ObservedProperty;
    }
    
    public String getJSONString(){
        return getJSONObject().toString();
    }
}
