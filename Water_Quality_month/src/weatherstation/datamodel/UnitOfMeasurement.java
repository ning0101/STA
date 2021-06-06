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
public class UnitOfMeasurement {
    public String name = null;
    public String symbol = null; 
    public String definition = null;

    public UnitOfMeasurement(String name, String symbol, String definition){
        this.name = name;
        this.symbol = symbol;
        this.definition = definition;
    }
    
    public JSONObject getJSONObject(){
        JSONObject UnitOfMeasurement = new JSONObject();
        UnitOfMeasurement.put("name", this.name);
        UnitOfMeasurement.put("symbol", this.symbol);
        UnitOfMeasurement.put("definition", this.definition);
        
        return UnitOfMeasurement;
    }
    
    public String getJSONString(){
        return getJSONObject().toString();
    }
}
