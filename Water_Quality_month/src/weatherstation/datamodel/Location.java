/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.datamodel;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author alec
 */
public class Location {
    public String name = null;
    public String description = null; 
    public String encodingType = null;
    public String cityencodingType = null;
    public String addressencodingType = null;
    public String type = null;
    public String cityType = null;
    public String addressType = null;
    public double longitude = Double.MAX_VALUE;
    public double latitude = Double.MAX_VALUE;
    public String city = null;
    public String address = null;

    public Location(String name, String description, String encodingType, String type, double longitude, double latitude){
        this.name = name;
        this.description = description;
        this.encodingType = encodingType;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    // city & address
    public Location(String name, String description, String cityencodingType, String cityType, String city, String addressencodingType, String addressType, String address){
        this.name = name;
        this.description = description;
        this.cityencodingType = cityencodingType;
        this.addressencodingType = addressencodingType;
        this.addressType = addressType;
        this.address = address;
        this.cityType = cityType;
        this.city = city;
    }
//    // city
//    public Location(String name, String description, String encodingType, String cityType, String city){
//        this.name = name;
//        this.description = description;
//        this.encodingType = encodingType;
//        this.cityType = cityType;
//        this.city = city;
//    }
    // address
    public Location(String name, String description, String addressencodingType, String addressType, String address){
        this.name = name;
        this.description = description;
        this.addressencodingType = addressencodingType;
        this.addressType = addressType;
        this.address = address;
    }
    
    public JSONObject getJSONObject(){
        JSONObject Location = new JSONObject();
        
        JSONObject location = new JSONObject();
        JSONArray coordinates = new JSONArray();              
        coordinates.put(this.longitude).put(this.latitude);
        location.put("type", this.type);
        location.put("coordinates", coordinates);
        Location.put("name", this.name);
        Location.put("description", this.description);
        Location.put("encodingType", encodingType);
        Location.put("location", location);
        
        return Location;
    }
    
    public String getJSONString(){
        return getJSONObject().toString();
    }
    
    public JSONObject getCityObject(){
        JSONObject cityLocation = new JSONObject();
        JSONObject location = new JSONObject();
        location.put("type", this.cityType);
        location.put("city", city);
        cityLocation.put("name", this.name);
        cityLocation.put("description", this.description);
        cityLocation.put("encodingType", cityencodingType);
        cityLocation.put("location", location);
        
        return cityLocation;
    }
    
    public String getCityString(){
        return getCityObject().toString();
    }
    public JSONObject getAddressObject(){
        JSONObject addressLocation = new JSONObject();
        JSONObject location = new JSONObject();
        location.put("type", this.addressType);
        location.put("address", this.address);
        addressLocation.put("name", this.name);
        addressLocation.put("description", this.description);
        addressLocation.put("encodingType", addressencodingType);
        addressLocation.put("location", location);
        
        return addressLocation;
    }
    
    public String getAddressString(){
        return getAddressObject().toString();
    }
}
