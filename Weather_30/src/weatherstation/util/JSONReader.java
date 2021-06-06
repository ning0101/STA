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
//	public static JSONArray locationData = new JSONArray();
	public JSONReader() {
		try {               
            //創建解析器
            JsonParser parser = new JsonParser();
            //創建JSON對象
            JsonObject object = (JsonObject) parser.parse(new FileReader("data/C-B0024-001.json"));
            //讀取鍵值對           
            String content = object.toString();
            data = new JSONObject(content);
    		locationArray = data.getJSONObject("cwbdata").getJSONObject("resources").getJSONObject("resource").getJSONObject("data").getJSONObject("surfaceObs").getJSONArray("location");
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
//	public static ArrayList<WeatherData> getData() {
//		ArrayList<WeatherData> stationData = new ArrayList<WeatherData>();
//		for(int i=0; i<locationData.length();i++) {
//			JSONArray observe_data = locationData.getJSONObject(i).getJSONArray("weatherElements");
//			HashMap<String,String> dataMap = new HashMap<String,String>();
//			for(int j=0;j<observe_data.length();j++) {
//				dataMap.put(observe_data.getJSONObject(j).getString("elementName"),observe_data.getJSONObject(j).getJSONObject("elementValue").getString("value"));
//			}
//			WeatherData weather_data = new WeatherData(locationArray.getJSONObject(i).getString("stationId"),locationArray.getJSONObject(i).getJSONObject("time").getString("obsTime"),dataMap.get("測站氣壓"),dataMap.get("溫度"),dataMap.get("相對濕度"),dataMap.get("風速"),dataMap.get("風向"),dataMap.get("降水量"));
//			stationData.add(weather_data);
//		}
//		
//		return stationData;
//	}
	public ArrayList<WeatherStation> getStation() {
		ArrayList<WeatherStation> stationArray = new ArrayList<WeatherStation>();
		for(int i=0; i<locationArray.length();i++) {
			JSONObject location = locationArray.getJSONObject(i).getJSONObject("station");
			WeatherStation station = new WeatherStation(location.getString("stationID"),location.getString("stationName"), location.getString("stationNameEN"), location.getString("stationAttribute"), locationArray.getJSONObject(i).getJSONObject("stationObsTimes").getJSONArray("stationObsTime"));
			stationArray.add(station);
		}
		return stationArray;
	}
	public ArrayList<WeatherData> getData() {
		ArrayList<WeatherData> obsdataArray = new ArrayList<WeatherData>();
		for(int i=0; i<locationArray.length();i++) {
			JSONArray location = locationArray.getJSONObject(i).getJSONObject("stationObsTimes").getJSONArray("stationObsTime");
			JSONObject pre = locationArray.getJSONObject(i).getJSONObject("station");
			JSONObject data = location.getJSONObject(i);
			String sunshineDuration = null;
			if(data.getJSONObject("weatherElements").get("sunshineDuration")!=null) {
				sunshineDuration = data.getJSONObject("weatherElements").get("sunshineDuration").toString();
			}
			WeatherData station = new WeatherData(pre.getString("stationID"),pre.getString("stationName"), data.getString("dataTime"), data.getJSONObject("weatherElements").getString("stationPressure"), data.getJSONObject("weatherElements").getString("temperature"),
					data.getJSONObject("weatherElements").getString("relativeHumidity"), data.getJSONObject("weatherElements").getString("windSpeed"), data.getJSONObject("weatherElements").getString("windDirectionDescription"),
					data.getJSONObject("weatherElements").getString("precipitation"), sunshineDuration);
			obsdataArray.add(station);
		}
		return obsdataArray;
	}

	public static JSONArray getLocationArray() {
		return locationArray;
	}
	public static void setLocationArray(JSONArray locationArray) {
		JSONReader.locationArray = locationArray;
	}
}

	