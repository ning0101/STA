package weatherstation.aqi.datamodel;

import org.json.JSONArray;
import org.json.JSONObject;
import weatherstation.datamodel.Location;


public class WeatherStation2 {
	private String stationID = null;
	private String stationName = null;
	private String stationNameEN = null;
	private String stationAttribute = null;
	public JSONArray stationObsTime = new JSONArray(); 
	
	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationNameEN() {
		return stationNameEN;
	}

	public void setStationNameEN(String stationNameEN) {
		this.stationNameEN = stationNameEN;
	}

	public String getStationAttribute() {
		return stationAttribute;
	}

	public void setStationAttribute(String stationAttribute) {
		this.stationAttribute = stationAttribute;
	}


	public WeatherStation2(String stationID, String stationName, String stationNameEN, String stationAttribute, JSONArray stationObsTime ) {
		this.stationID = stationID;
		this.stationName = stationName;
		this.stationNameEN = stationNameEN;
		this.stationAttribute = stationAttribute;
		this.stationObsTime = stationObsTime;
	}
	
	public String getSTAThingEntity() {
		JSONObject thingObj = new JSONObject();
		String name = "氣象站(月)-" +  this.stationName +"-"+ this.stationID;
		String description = "氣象站(月)-" + this.stationName+"-"+ this.stationID;
		JSONObject propertiesObj = new JSONObject();
        propertiesObj.put("authority", "中央氣象局");
		propertiesObj.put("stationID", this.stationID);
		propertiesObj.put("stationName", this.stationName);
		propertiesObj.put("stationNameEN", this.stationNameEN);
		propertiesObj.put("stationAttribute", this.stationAttribute);
//		propertiesObj.put("township", this.TOWN);
//		propertiesObj.put("townshipSN", this.TOWN_SN);
//  

		thingObj.put("name", name);
		thingObj.put("description", description);
		thingObj.put("properties", propertiesObj);

		JSONArray Datastreams = new JSONArray();
		
        String sensorName = "氣象站(月)-"+this.stationName +"-"+ this.stationID; //"WaterLevel sensor";
        String sensorDescription = "氣象站(月)-"+this.stationName+"-"+ this.stationID;
        String sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";
//		sensor.addToJSONArray("identifier", stationId);
//		sensor.addToJSONArray("identification", stationId);
//		sensor.addToJSONArray("keyword", stationId);
//		sensor.addToJSONArray("identifier", locationName);
//		sensor.addToJSONArray("identification", locationName);
//		sensor.addToJSONArray("keyword", locationName);
//		sensor.addToJSONArray("Classification", datastreamName);

        // datastream 1 - stationPressure 測站氣壓(百怕,hpa)
        String datastreamName = "測站氣壓";
        String datastreamDescription = "氣象站(月)-"+this.stationName +"-"+ this.stationID +"-"+ datastreamName; 
        String datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        String unitOfMeasurementName ="pascal";
        String unitOfMeasurementSymbol ="hPa";
        String unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Pascal_(unit)"; 
        // Datastream ObservedProperty
        String observedPropertyName = "測站氣壓";
        String observedPropertyDefinition = "https://en.wikipedia.org/wiki/Pressure";
        String observedPropertyDescription = "";

        
        // datastream 2 - temperature 測站溫度(攝氏，℃)
        datastreamName = "測站溫度";
        datastreamDescription = "氣象站(月)-"+this.stationName +"-"+ this.stationID +"-"+ datastreamName;
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="celsius";
        unitOfMeasurementSymbol ="℃";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Celsius";
        // Datastream ObservedProperty
        observedPropertyName = "氣溫";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Temperature";
        observedPropertyDescription = "";
        
               
        // datastream 3 - relativeHumidity 相對濕度(百分比率，%)
        datastreamName = "相對濕度";
        datastreamDescription = "氣象站(月)-"+this.stationName +"-"+ this.stationID +"-"+ datastreamName;
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="percentage";
        unitOfMeasurementSymbol ="%";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Percentage";
        // Datastream ObservedProperty
        observedPropertyName = "相對溼度";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Humidity";
        observedPropertyDescription = "";
        
        // datastream 4 - windSpeed 測站風速(公尺/秒)
        datastreamName = "測站風速";
        datastreamDescription = "氣象站(月)-"+this.stationName +"-"+ this.stationID +"-"+ datastreamName; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="metre per second";
        unitOfMeasurementSymbol ="m/s";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Metre_per_second";
        // Datastream ObservedProperty
        observedPropertyName = "測站風速";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Wind_speed";
        observedPropertyDescription = "";       
                 
        // datastream 5 - windDirectionDescription 測站風向描述
        datastreamName = "測站風向描述";
        datastreamDescription = "氣象站(月)-"+this.stationName +"-"+ this.stationID +"-"+ datastreamName; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="azimuth";
        unitOfMeasurementSymbol ="degrees";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Azimuth";
        // Datastream ObservedProperty
        observedPropertyName = "測站風向描述";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Wind_direction";
        observedPropertyDescription = "";

        // datastream 6 - precipitation 降水量
        datastreamName = "降水量";
        datastreamDescription = "氣象站(月)-"+this.stationName +"-"+ this.stationID +"-"+ datastreamName; 
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="Millimetre";
        unitOfMeasurementSymbol ="mm";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Millimetre";
        // Datastream ObservedProperty
        observedPropertyName = "降水量";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Rain";
        observedPropertyDescription = "";       
        
        // datastream 7 - sunshineDuration 日照時數
        datastreamName = "測站日照時數";
        datastreamDescription = "氣象站(月)-"+this.stationName +"-"+ this.stationID +"-"+ datastreamName;
        datastreamObservationType = "http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="hour";
        unitOfMeasurementSymbol ="hr";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Hour";
        // Datastream ObservedProperty
        observedPropertyName = "測站日照時數";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Sunlight";
        observedPropertyDescription = "";

//---------------------------------------------------------------------------------------------

		thingObj.put("Datastreams", Datastreams);

		JSONArray Locations = new JSONArray();
		Location location = new Location("氣象站(月)-" +this.stationName+"-"+this.stationID, "氣象站(月)-"+ this.stationName+"-"+this.stationID, "application/vnd.geo+json","");

		Locations.put(location.getJSONObject());

		thingObj.put("Locations", Locations);

		String data = thingObj.toString();

		return data;
	}




}