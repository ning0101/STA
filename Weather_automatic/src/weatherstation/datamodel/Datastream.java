/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.datamodel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alec
 */
public class Datastream {
	public static HashMap<String, Integer> datastreams = new HashMap<String, Integer>();
    private int id = 0;
    public String name = null;
    public String description = null; 
    public String observationType = null;
    public UnitOfMeasurement unitOfMeasurement = null;
    public Sensor sensor = null;
    public ObservedProperty observedProperty = null;
    
    private ArrayList<String> timeStringList = new ArrayList<String>();
    private ArrayList<Double> resultList = new ArrayList<Double>();
    
    public Datastream(int id){
        this.id = id;
    }
    public Datastream(String name, String description, String observationType){
        this.name = name;
        this.description = description;
        this.observationType = observationType;
    }
    public Datastream(String name, String description, String observationType, 
            UnitOfMeasurement unitOfMeasurement, Sensor sensor, ObservedProperty observedProperty){
        this.name = name;
        this.description = description;
        this.observationType = observationType;
        this.unitOfMeasurement = unitOfMeasurement;
        this.sensor = sensor;
        this.observedProperty = observedProperty;
    }
    public Datastream(int id,
            String timeString,
            double result){
        this.id = id;
        this.timeStringList.add(timeString);
        this.resultList.add(result);
    }

    public void addData(String timeString, double result){
        this.timeStringList.add(timeString);
        this.resultList.add(result);
    }
    
    public JSONObject getJSONObject(){
        JSONObject Datastream = new JSONObject();
        if(this.id>0) {
        	Datastream.put("@iot.id",this.id);
        }else {
        	Datastream.put("name", this.name);
	        Datastream.put("description", this.description);
	        Datastream.put("observationType", this.observationType);
	
	        Datastream.put("unitOfMeasurement", this.unitOfMeasurement.getJSONObject());
	
	       
//	        Datastream.put("Sensor", this.sensor.getJSONObject());
//	        System.out.print(this.sensor.getJSONObject());
	        
	        JSONObject test = new JSONObject();
	        test.put("@iot.id", 1);
	        //test.put
	        Datastream.put("Sensor", test);
	        
	        Datastream.put("ObservedProperty", this.observedProperty.getJSONObject());
        }
        
        return Datastream;
    }
    
    public String getJSONString(){
        return getJSONObject().toString();
    }
    
    public JSONObject getSTAInsertObservationsJSONObject() throws JSONException, ParseException{
        JSONObject obj = new JSONObject();
        
        JSONObject datastreamObj = new JSONObject();
        datastreamObj.put("@iot.id", this.id);
        obj.put("Datastream", datastreamObj);
        
        JSONArray componentsArray = new JSONArray();
        componentsArray.put("phenomenonTime");
        componentsArray.put("result");
        obj.put("components", componentsArray);
        
        obj.put("dataArray@iot.count", this.resultList.size());
        
        JSONArray dataArrayArray = new JSONArray();
        for(int i=0; i<this.timeStringList.size(); i++){
            JSONArray dataArray = new JSONArray();
            dataArray.put(this.timeStringList.get(i));
            dataArray.put(this.resultList.get(i));
            
            dataArrayArray.put(dataArray);
        }
        obj.put("dataArray", dataArrayArray);
        
        return obj;
    }
    
    public String getSTAInsertObservationsString() throws JSONException, ParseException{
        return getSTAInsertObservationsJSONObject().toString();
    }
    
    public static void main(String[] args) {
        Datastream datastream = new Datastream(1);
        datastream.addData("2010-12-23T10:20:00-0700", 20.2);
        datastream.addData("2010-12-23T10:21:00-0700", 30.3);
        
        try {
            System.out.println(datastream.getSTAInsertObservationsString());
        } catch (JSONException ex) {
            Logger.getLogger(Datastream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Datastream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
