package weatherstation.aqi.datamodel;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import weatherstation.aqi.Cache;
import weatherstation.aqi.datamodel.*;
import weatherstation.datamodel.Datastream;
import weatherstation.datamodel.Location;
import weatherstation.datamodel.ObservedProperty;
import weatherstation.datamodel.Sensor;
import weatherstation.datamodel.UnitOfMeasurement;
import weatherstation.util.HTTPConnector;
import weatherstation.util.Properties;


public class WeatherStation {
	private String stationId = null;
	private String locationName = null;
	private String CITY = null;
	private String CITY_SN = null;
	private String TOWN = null;
	private String TOWN_SN = null;
	private String elementName = null;
	private double lat = Double.MAX_VALUE;
	private double lon = Double.MAX_VALUE;
	private double lat_wgs84 = Double.MAX_VALUE;
	private double lon_wgs84 = Double.MAX_VALUE;
	private double ELEV = Double.MAX_VALUE;
	public ArrayList<String> DatastreamsNames = new ArrayList<String>();
	public ArrayList<String> classification = new ArrayList<String>();
	private JSONArray Datastreams = new JSONArray();
	
	public String getCITY_SN() {
		return CITY_SN;
	}

	public void setCITY_SN(String cITY_SN) {
		CITY_SN = cITY_SN;
	}

	public String getTOWN() {
		return TOWN;
	}

	public void setTOWN(String tOWN) {
		TOWN = tOWN;
	}

	public String getTOWN_SN() {
		return TOWN_SN;
	}

	public void setTOWN_SN(String tOWN_SN) {
		TOWN_SN = tOWN_SN;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat_wgs84() {
		return lat_wgs84;
	}

	public void setLat_wgs84(double lat_wgs84) {
		this.lat_wgs84 = lat_wgs84;
	}

	public double getLon_wgs84() {
		return lon_wgs84;
	}

	public void setLon_wgs84(double lon_wgs84) {
		this.lon_wgs84 = lon_wgs84;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}
	
	public double getELEV() {
		return ELEV;
	}

	public void setELEV(double eLEV) {
		ELEV = eLEV;
	}

	public WeatherStation(String lat, String lon, String lat_wgs84, String lon_wgs84,  String locationName, String stationId, String CITY, String CITY_SN, String TOWN, String TOWN_SN, String elementName, String ELEV) {
		this.stationId = stationId;
		this.locationName = locationName;
		this.lat = Double.valueOf(lat);
		this.lon = Double.valueOf(lon);
		this.lat_wgs84 = Double.valueOf(lat_wgs84);
		this.lon_wgs84 = Double.valueOf(lon_wgs84);
		this.setCITY(CITY);
		this.CITY_SN = CITY_SN;
		this.TOWN = TOWN;
		this.TOWN_SN = TOWN_SN;
		this.elementName = elementName;
		this.ELEV = Double.valueOf(ELEV);
	}
	
	public String getSTAThingEntity() throws Exception {
		JSONObject thingObj = new JSONObject();
		String name = "自動氣象站-" + this.locationName +"-"+ this.stationId;
		String description = "自動氣象站-" + this.locationName + "-"+ this.stationId;
		JSONObject propertiesObj = new JSONObject();
        propertiesObj.put("authority", "中央氣象局");
		propertiesObj.put("stationID", this.stationId);
		propertiesObj.put("stationName", this.locationName);
		propertiesObj.put("city", this.CITY);
		propertiesObj.put("citySN", this.CITY_SN);
		propertiesObj.put("township", this.TOWN);
		propertiesObj.put("townshipSN", this.TOWN_SN);
		propertiesObj.put("AGL", this.ELEV);

		thingObj.put("name", name);
		thingObj.put("description", description);
		thingObj.put("properties", propertiesObj);

		JSONArray Datastreams = new JSONArray();
		
        String sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        String sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        String sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
        // datastream 2 - WDIR 平均風風向(度，degree;風向0表無風)
        String datastreamName = "平均風風向";
        String datastreamDescription = "自動氣象站-" +this.locationName +"-" + this.stationId + "-平均風風向"; 
        String datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        String unitOfMeasurementName ="azimuth";
        String unitOfMeasurementSymbol ="degrees";
        String unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Azimuth";
        // Datastream ObservedProperty
        String observedPropertyName = "平均風風向";
        String observedPropertyDefinition = "https://en.wikipedia.org/wiki/Wind_direction";
        String observedPropertyDescription = "Wind_direction";
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));
        
        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
        // datastream 3 - WDSD 平均風風速(公尺/秒)
        datastreamName = "平均風風速";
        datastreamDescription = "自動氣象站-" +this.locationName +"-" + this.stationId + "-平均風風速"; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="metre per second";
        unitOfMeasurementSymbol ="m/s";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Metre_per_second";
        // Datastream ObservedProperty
        observedPropertyName = "平均風風速";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Wind_speed";
        observedPropertyDescription = "";
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));
        
        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";               
        // datastream 4 - TEMP 氣溫(攝氏，℃)
        datastreamName = "氣溫";
        datastreamDescription = "自動氣象站-" +this.locationName +"-" + this.stationId + "-氣溫"; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="celsius";
        unitOfMeasurementSymbol ="℃";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Celsius";
        // Datastream ObservedProperty
        observedPropertyName = "氣溫";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Temperature";
        observedPropertyDescription = "";
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));
        
        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
        // datastream 5 - HUMD 相對濕度(百分比率，%)
        datastreamName = "相對濕度";
        datastreamDescription = "自動氣象站-" +this.locationName +"-" + this.stationId + "-相對濕度"; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="percentage";
        unitOfMeasurementSymbol ="%";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Percentage";
        // Datastream ObservedProperty
        observedPropertyName = "相對溼度";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Humidity";
        observedPropertyDescription = "Humidity";
    
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));
        
        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
        // datastream 6 - PRES 氣壓(百帕，hPa)
        datastreamName = "氣壓";
        datastreamDescription ="自動氣象站-" +this.locationName +"-" + this.stationId +"-氣壓"; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="pascal";
        unitOfMeasurementSymbol ="hPa";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Pascal_(unit)";
        // Datastream ObservedProperty
        observedPropertyName = "氣壓";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Pressure";
        observedPropertyDescription = "Pressure";
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));
        
        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
        // datastream 7 - H_24R 日累積雨量(mm)
        datastreamName = "日累積雨量";
        datastreamDescription = "自動氣象站-" +this.locationName +"-" + this.stationId +"-日累積雨量"; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="millimetre";
        unitOfMeasurementSymbol ="mm";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Millimetre";
        // Datastream ObservedProperty
        observedPropertyName = "日累積雨量";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Rain";
        observedPropertyDescription = "";
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));
        
        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
        // datastream 8 - H_FX 小時瞬間最大陣風風速(m/s)
        datastreamName = "小時瞬間最大陣風風速";
        datastreamDescription = "自動氣象站-" +this.locationName +"-" + this.stationId +"-小時瞬間最大陣風風速"; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="metre per second";
        unitOfMeasurementSymbol ="m/s";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Metre_per_second";
        // Datastream ObservedProperty
        observedPropertyName = "小時瞬間最大陣風風速";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Wind_speed";
        observedPropertyDescription = "";
        /////////////////////////////////////////
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));       
        
        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
        // datastream 9 - H_XD 小時瞬間最大陣風風向 (degree)
        datastreamName = "小時瞬間最大陣風風向";
        datastreamDescription = "自動氣象站-" +this.locationName +"-" + this.stationId +"-小時瞬間最大陣風風向"; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="azimuth";
        unitOfMeasurementSymbol ="degrees";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Azimuth";
        // Datastream ObservedProperty
        observedPropertyName = "小時瞬間最大陣風風向";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Wind_direction";
        observedPropertyDescription = "";
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));
        
//        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
//        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
//        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
//        // datastream 10 - H_FXT 小時瞬間最大陣風發生時間(小時分鐘，hhmm)
//        datastreamName = "H_FXT";
//        datastreamDescription = "小時瞬間最大陣風發生時間"; 
//        datastreamObservationType = "https://en.wikipedia.org/wiki/Wind_direction";
//        // Datastream unitOfMeasurement
//        unitOfMeasurementName ="hours and minutes";
//        unitOfMeasurementSymbol ="hhmm";
//        unitOfMeasurementDefinition ="";
//        // Datastream ObservedProperty
//        observedPropertyName = "H_FXT";
//        observedPropertyDefinition = "";
//        observedPropertyDescription = "H_FXT 小時瞬間最大陣風發生時間";
//        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
//				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));       
//        
        
        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
        // datastream 15 - D_TX 本日最高溫度(攝氏，℃)
        datastreamName = "本日最高溫度";
        datastreamDescription = "自動氣象站-" +this.locationName +"-" + this.stationId +"-本日最高溫度"; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="Celsius";
        unitOfMeasurementSymbol ="℃";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Celsius";
        // Datastream ObservedProperty
        observedPropertyName = "本日最高溫度";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Temperature";
        observedPropertyDescription = "";
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));       
         
//        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId;; //"WaterLevel sensor";
//        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
//        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";               
//        // datastream 16 - D_TXT 本日最高溫度發生時間(小時分鐘，hhmm)
//        datastreamName = "D_TXT";
//        datastreamDescription = "本日最高溫度發生時間"; 
//        datastreamObservationType = "";
//        // Datastream unitOfMeasurement
//        unitOfMeasurementName ="hours and minutes";
//        unitOfMeasurementSymbol ="hhmm";
//        unitOfMeasurementDefinition ="";
//        // Datastream ObservedProperty
//        observedPropertyName = "D_TXT";
//        observedPropertyDefinition = "";
//        observedPropertyDescription = "D_TXT 本日最高溫度發生時間";
//        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
//				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));       
//        
        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId; //"WaterLevel sensor";
        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
        // datastream 17 - D_TN 本日最低溫度(攝氏，℃)
        datastreamName = "本日最低溫度";
        datastreamDescription = "自動氣象站-" +this.locationName +"-" + this.stationId +"-本日最低溫度"; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="Celsius";
        unitOfMeasurementSymbol ="℃";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Celsius";
        // Datastream ObservedProperty
        observedPropertyName = "本日最低溫度";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Temperature";
        observedPropertyDescription = "";
        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));       
        
//        sensorName = "自動氣象站-" +this.locationName +"-" + this.stationId;; //"WaterLevel sensor";
//        sensorDescription = "自動氣象站-" +this.locationName +"-" + this.stationId;
//        sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";       
//        // datastream 18 - D_TNT 本日最低溫度發生時間(小時分鐘，hhmm)
//        datastreamName = "D_TNT";
//        datastreamDescription = "本日最低溫度發生時間"; 
//        datastreamObservationType = "";
//        // Datastream unitOfMeasurement
//        unitOfMeasurementName ="hours and minutes";
//        unitOfMeasurementSymbol ="hhmm";
//        unitOfMeasurementDefinition ="";
//        // Datastream ObservedProperty
//        observedPropertyName = "D_TNT";
//        observedPropertyDefinition = "";
//        observedPropertyDescription = "D_TNT 本日最低溫度發生時間";
//        Datastreams.put(createNewDatastream(sensorName, sensorDescription,sensorEncodingType,datastreamName,datastreamDescription,datastreamObservationType,unitOfMeasurementName,unitOfMeasurementSymbol,
//				unitOfMeasurementDefinition,observedPropertyName,observedPropertyDefinition,observedPropertyDescription));       
//        

//---------------------------------------------------------------------------------------------

		thingObj.put("Datastreams", Datastreams);
		
		Location location;
		JSONArray Locations = new JSONArray();
		if(Location.locations.containsKey("自動氣象站-"+this.locationName + this.stationId)) {
			location = new Location(Location.locations.get("自動氣象站-" + this.stationId));
		}else {
			location = new Location("自動氣象站-" + this.locationName+"-"+this.stationId , "自動氣象站-" + this.locationName+"-"+this.stationId, "application/vnd.geo+json",
					"Point", this.lon_wgs84, this.lat_wgs84);
			HTTPConnector http = new HTTPConnector();
			http.sendPost(Properties.URL_STA+"/Locations", location.getJSONObject().toString());
			Cache.updateLocation();
			location = new Location(Location.locations.get("自動氣象站-" + this.locationName+"-"+this.stationId));
		}
		Locations.put(location.getJSONObject());
		thingObj.put("Locations", Locations);

		String data = thingObj.toString();

		return data;
	}

	public JSONObject createNewDatastream(String sensorName, String sensorDescription, String sensorEncodingType, String datastreamName
			, String datastreamDescription, String datastreamObservationType,String unitOfMeasurementName, String unitOfMeasurementSymbol, String unitOfMeasurementDefinition, 
			String observedPropertyName,String observedPropertyDefinition,String observedPropertyDescription) throws Exception {
		Sensor sensor;
		if(Sensor.sensors.containsKey(sensorName)) {
			sensor = new Sensor(Sensor.sensors.get(sensorName));
		}else {
	        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType);
	        sensor.addToJSONArray("identifier", stationId);
			sensor.addToJSONArray("identification", stationId);
			sensor.addToJSONArray("keyword", stationId);
			sensor.addToJSONArray("identification", locationName);
			sensor.addToJSONArray("keyword", locationName);
			sensor.addToJSONArray("haracteristics", CITY);
			sensor.addToJSONArray("Classification", "平均風風向，平均風風速，氣溫，相對濕度，氣壓，日累積雨量,小時瞬間最大陣風風速,小時瞬間最大陣風風向,本日最高溫度,本日最低溫度");
//			sensor.addToJSONArray("keyword",datastreamName);
			sensor.addToJSONArray("Characteristics", CITY_SN);
			sensor.addToJSONArray("Characteristics", TOWN);
			sensor.addToJSONArray("Characteristics", TOWN_SN);
			
			
			HTTPConnector http = new HTTPConnector();
			String newSensorString = sensor.getJSONObject().toString();			
			http.sendPost(Properties.URL_STA+"/Sensors", newSensorString);				
			Cache.updateSensor();

			
		}
		UnitOfMeasurement uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
		ObservedProperty observedProperty;
		if(ObservedProperty.observedProperties.containsKey(observedPropertyName)) {
			observedProperty = new ObservedProperty(ObservedProperty.observedProperties.get(observedPropertyName));
		}else {
			observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition,
				observedPropertyDescription);
		}
		Datastream datastream;
		if(Datastream.datastreams.containsKey(datastreamDescription)) {
			datastream = new Datastream(Datastream.datastreams.get(datastreamDescription));
		}else {
			datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor,
							observedProperty);
			HTTPConnector http = new HTTPConnector();
			String newDatastream = datastream.getJSONObject().toString();
		}
		return datastream.getJSONObject();
	}

	public void setClassification(ArrayList<String> arrayList) {
		this.classification = arrayList;
	}

}