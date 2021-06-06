package weatherstation.util;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import org.json.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.opencsv.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import weatherstation.aqi.datamodel.WeatherStation;
import weatherstation.aqi.datamodel.WeatherData;
import weatherstation.aqi.datamodel.AQIData;
import weatherstation.aqi.datamodel.WeatherData;
import weatherstation.datamodel.*;

public class JSONReader {
	
    
	public JSONObject data = new JSONObject();
	public static JSONArray locationArray = new JSONArray();
	public JSONReader() {
		try {               
            //創建解析器
            JsonParser parser = new JsonParser();
            //創建JSON對象
            JsonObject object = (JsonObject) parser.parse(new FileReader("data/O-A0003-001.json"));
            //讀取鍵值對
//            System.out.println("location=" +object.get("location").getAsString());
//            System.out.println("locationName=" +object.get("locationName").getAsString());           
            String content = object.toString();
            data = new JSONObject(content);
    		locationArray = data.getJSONObject("cwbopendata").getJSONArray("location");
            //讀取數組中的數據
//            JSONArray locationArray = data.getJSONObject("cwbopendata").getJSONArray("location");
            //用for循環來讀取數組中的數據
//            for (int i = 0; i < locationArray.length(); i++) {
//                System.out.println("-------");
//                JSONObject subObject = locationArray.getJSONObject(i);
//                System.out.println(subObject.getString("locationName"));
//                System.out.println(subObject.getString("stationId"));
//                
//                JSONArray weatherElements = subObject.getJSONArray("weatherElement");
//                for(int j =0; j<weatherElements.length(); j++) {
//                	JSONObject weatherElement = weatherElements.getJSONObject(j);
//                	System.out.println(weatherElement.getString("elementName")+" : "+weatherElement.getJSONObject("elementValue").get("value"));
//                	}
//
//                JSONArray parameters = subObject.getJSONArray("parameter");
//                for(int j =0; j<parameters.length(); j++) {
//                	JSONObject parameter = parameters.getJSONObject(j);
//                	System.out.println(parameter.getString("parameterName") + " : " + parameter.getString("parameterValue"));
//                }
//            }
            
                                      
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	public static ArrayList<WeatherData> getData() {
		ArrayList<WeatherData> stationData = new ArrayList<WeatherData>();
		for(int i=0; i<locationArray.length();i++) {
			JSONArray observe_data = locationArray.getJSONObject(i).getJSONArray("weatherElement");
			HashMap<String,String> dataMap = new HashMap<String,String>();
			for(int j=0;j<observe_data.length();j++) {
				dataMap.put(observe_data.getJSONObject(j).getString("elementName"),observe_data.getJSONObject(j).getJSONObject("elementValue").getString("value"));
			}
			WeatherData weather_data = new WeatherData(locationArray.getJSONObject(i).getString("stationId"),locationArray.getJSONObject(i).getString("locationName"),locationArray.getJSONObject(i).getJSONObject("time").getString("obsTime"),dataMap.get("WDIR"),dataMap.get("WDSD"),dataMap.get("TEMP"),dataMap.get("HUMD"),dataMap.get("PRES"),dataMap.get("24R"),dataMap.get("H_FX"),dataMap.get("H_XD"),dataMap.get("H_FXT"),dataMap.get("H_F10"),dataMap.get("H_10D"),dataMap.get("H_F10T"),dataMap.get("H_UVI"),dataMap.get("D_TX"),dataMap.get("D_TXT"),dataMap.get("D_TN"),dataMap.get("D_TNT"),dataMap.get("VIS"),dataMap.get("Weather"));
			stationData.add(weather_data);
		}
		
		return stationData;
	}
	public ArrayList<WeatherStation> getStation() {
		ArrayList<WeatherStation> stationArray = new ArrayList<WeatherStation>();
		ArrayList<WeatherData> stationData = new ArrayList<WeatherData>();
		for(int i=0; i<locationArray.length();i++) {
			JSONObject location = locationArray.getJSONObject(i);
			JSONArray observe_data = locationArray.getJSONObject(i).getJSONArray("weatherElement");
			HashMap<String, String> locationMap = new HashMap<String, String>();
			HashMap<String,String> dataMap = new HashMap<String,String>();
			for(int j=0;j<observe_data.length();j++) {
				dataMap.put(observe_data.getJSONObject(j).getString("elementName"),observe_data.getJSONObject(j).getJSONObject("elementValue").getString("value"));
			}
			for(int j=0; j<location.getJSONArray("parameter").length();j++) {
				locationMap.put(location.getJSONArray("parameter").getJSONObject(j).getString("parameterName"), location.getJSONArray("parameter").getJSONObject(j).getString("parameterValue"));
			}
			ArrayList<String> weatherNameArray = new ArrayList<String>(); 
			for(int k=0; k<location.getJSONArray("weatherElement").length();k++) {
				weatherNameArray.add(location.getJSONArray("weatherElement").getJSONObject(k).getString("elementName"));
			}
			WeatherStation station = new WeatherStation(location.getString("lat"),location.getString("lon"),location.getString("lat_wgs84"),location.getString("lon_wgs84"),location.getString("locationName"),location.getString("stationId"), locationMap.get("CITY"), locationMap.get("CITY_SN"), locationMap.get("TOWN"), locationMap.get("TOWN_SN"),dataMap.get("ELEV"),weatherNameArray.toString());
			stationArray.add(station);
		}
		return stationArray;
	}
}


	