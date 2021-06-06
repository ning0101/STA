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
public class ObservedProperty {
    public String name = null;
    public String definition = null;
    public String description = null; 

    public ObservedProperty(String name, String description, String definition){
        this.name = name;
        this.description = description;
        this.definition = definition;
    }
    
    public JSONObject getJSONObject(){
        JSONObject ObservedProperty = new JSONObject();
        
        ObservedProperty.put("name", name);
        ObservedProperty.put("definition", definition);
        ObservedProperty.put("description", description);
        
        return ObservedProperty;
    }
    
    public String getJSONString(){
        return getJSONObject().toString();
    }
}
