/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.aqi.datamodel;

import java.text.ParseException;
import java.util.ArrayList;

import weatherstation.datamodel.Datastream;
import weatherstation.datamodel.Location;
import weatherstation.datamodel.ObservedProperty;
import weatherstation.datamodel.Sensor;
import weatherstation.datamodel.UnitOfMeasurement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;

/**
 *
 * @author alec
 */
public class AQIStation {
	public String DamName = null;
//	public String station_name = null;
	public String County = null;
	public String SiteEngName = null;
	public int SiteId = (int) Double.MAX_VALUE;
	public String SiteName = null;
	public String Township = null;
	private double TWD97Lat = Double.MAX_VALUE;
	private double TWD97Lon = Double.MAX_VALUE;
	private double TWD97TM2X = Double.MAX_VALUE;
	private double TWD97TM2Y = Double.MAX_VALUE;
	
	// weather_station_metro.csv
	public AQIStation(WeatherStation weatherStation) {
		this.County = weatherStation.getCounty();
		this.DamName = weatherStation.getDamName();
		this.SiteEngName = weatherStation.getSiteEngName();
		this.SiteId = weatherStation.getSiteId();
		this.SiteName = weatherStation.getSiteName();
		this.Township = weatherStation.getTownship();
		this.TWD97Lat = weatherStation.getTWD97Lat();
		this.TWD97Lon = weatherStation.getTWD97Lon();
		this.TWD97TM2X = weatherStation.getTWD97TM2X();
		this.TWD97TM2Y = weatherStation.getTWD97TM2Y();
	}
//    public AQIStation(String jsonString) throws JSONException, ParseException{
//        JSONObject obj = new JSONObject(jsonString);
//        this.parseJSONObject(obj);
//    }
//    public AQIStation(JSONObject obj) throws JSONException, ParseException{
//        this.parseJSONObject(obj);
//    }
    
    
    public String getSTAThingEntity(){
        JSONObject thingObj = new JSONObject();
        String name = "城市-"+this.County;
        String description = "水庫-"+this.DamName;
        JSONObject propertiesObj = new JSONObject();
//        propertiesObj.put("authority", "銵��憓�風蝵�");
        propertiesObj.put("County", this.County);
        propertiesObj.put("DamName", this.DamName);
//        propertiesObj.put("ItemEngAbbreviation", this.ItemEngAbbreviation);
//        propertiesObj.put("ItemEngName", this.ItemEngName);
//        propertiesObj.put("ItemName", this.ItemName);
//        propertiesObj.put("ItemUnit", this.ItemUnit);
//        propertiesObj.put("ItemValue", this.ItemValue);
//        propertiesObj.put("SampleDate", this.SampleDate);
//        propertiesObj.put("SampleDepth", this.SampleDepth);
//        propertiesObj.put("SampleLayer", this.SampleLayer);
        propertiesObj.put("SiteEngName", this.SiteEngName);
        propertiesObj.put("SiteId", this.SiteId);        
        propertiesObj.put("SiteName", this.SiteName);
        propertiesObj.put("Township", this.Township);
        propertiesObj.put("TWD97Lat", this.TWD97Lat);
        propertiesObj.put("TWD97Lon", this.TWD97Lon);
        propertiesObj.put("TWD97TM2X", this.TWD97TM2X);
        propertiesObj.put("TWD97TM2Y", this.TWD97TM2Y);
        
        thingObj.put("name", name);
        thingObj.put("description", description);
        thingObj.put("properties", propertiesObj);
        
        JSONArray Datastreams = new JSONArray();
        
        // datastream 1 - ELEV 擃漲(�撠綽�)
        String datastreamName = "ELEV";
        String datastreamDescription = "擃漲"; 
        String datastreamObservationType = "https://en.wikipedia.org/wiki/Height";
        // Datastream unitOfMeasurement
        String unitOfMeasurementName ="meter";
        String unitOfMeasurementSymbol ="m";
        String unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Metre";
        // Datastream Sensor
//        String sensorName = "AQI Algorithm";
//        String sensorDescription = "蝛箸除��釭����";
//        String sensorEncodingType = "text/plain";
//        String sensorMetadata = "蝛箸除��釭���靘�皜祈����蝛箸除銝剛瘞�(O3)�敦�瘚桀凝蝎�(PM2.5)�瘚桀凝蝎�(PM10)��瘞批�４(CO)��飢��‵(SO2)���飢��乾(NO2)瞈漲蝑�潘�誑�撠犖擃摨瑞�蔣�蝔漲嚗����銝�情��銋����潘��誑���������憭批�潛閰脫葫蝡�銋征瘞���釭�����(AQI)��";
        
//      SensorMLAdapter sensorMLAdapter = new SensorMLAdapter(this);
//      SensorML sensorML = sensorMLAdapter.createSensorML();
//		String sensorMetadata = sensorML.getJSONString();
        String sensorName = "撅�撅祆除鞊∠��"; //"WaterLevel sensor";
        String sensorDescription = "撅�撅祆除鞊∠��";
        String sensorEncodingType = "http://www.opengis.net/doc/IS/SensorML/2.0"; //"text/plain";
//      Sensor sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorML.getJSONObject());
        Sensor sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, "");
        
        // Datastream ObservedProperty
        String observedPropertyName = "ELEV";
        String observedPropertyDefinition = "";
        String observedPropertyDescription = "ELEV 擃漲";

        UnitOfMeasurement uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
        ObservedProperty observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        Datastream datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 2 - WDIR 撟喳�◢憸典��(摨佗�egree;憸典��0銵函憸�)
        datastreamName = "WDIR";
        datastreamDescription = "撟喳�◢憸典��"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Wind_direction";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="azimuth";
        unitOfMeasurementSymbol ="degrees";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Azimuth";
        // Datastream Sensor
//        sensorName = "Pollutant sensor";
//        sensorDescription = "Pollutant sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "Pollutant sensor";

        // Datastream ObservedProperty
        observedPropertyName = "WDIR";
        observedPropertyDefinition = "";
        observedPropertyDescription = "WDIR 撟喳�◢憸典��";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 3 - WDSD 撟喳�◢憸券��(�撠�/蝘�)
        datastreamName = "WDSD";
        datastreamDescription = "撟喳�◢憸券��"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Wind_speed";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="metre per second";
        unitOfMeasurementSymbol ="m/s";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Metre_per_second";
        // Datastream Sensor
//        sensorName = "Status";
//        sensorDescription = "Air quality status";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "Air quality status";
        // Datastream ObservedProperty
        observedPropertyName = "WDSD";
        observedPropertyDefinition = "";
        observedPropertyDescription = "WDSD 撟喳�◢憸券��";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
                
        // datastream 4 - TEMP 瘞�皞�(������)
        datastreamName = "TEMP";
        datastreamDescription = "瘞�皞�"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Temperature";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="celsius";
        unitOfMeasurementSymbol ="���";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Celsius";
        // Datastream Sensor
//        sensorName = "CO sensor";
//        sensorDescription = "CO sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "CO sensor";
        // Datastream ObservedProperty
        observedPropertyName = "TEMP";
        observedPropertyDefinition = "";
        observedPropertyDescription = "TEMP 瘞�皞�";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 5 - HUMD �撠獐摨�(�������%)
        datastreamName = "HUMD";
        datastreamDescription = "�撠獐摨�"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Humidity";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="percentage";
        unitOfMeasurementSymbol ="%";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Percentage";
        // Datastream Sensor
//        sensorName = "CO sensor";
//        sensorDescription = "CO sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "CO sensor";
        // Datastream ObservedProperty
        observedPropertyName = "HUMD";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Humidity";
        observedPropertyDescription = "HUMD �撠獐摨�";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 6 - PRES 瘞�憯�(�撣�Pa)
        datastreamName = "PRES";
        datastreamDescription = "瘞�憯�"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Pressure";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="pascal";
        unitOfMeasurementSymbol ="hPa";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Pascal_(unit)";
        // Datastream Sensor
//        sensorName = "O3 sensor";
//        sensorDescription = "O3 sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "O3 sensor";
        // Datastream ObservedProperty
        observedPropertyName = "PRES";
        observedPropertyDefinition = "https://en.wikipedia.org/wiki/Pressure";
        observedPropertyDescription = "PRES 瘞�憯�";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 7 - 24R �蝝舐����(mm)
        datastreamName = "24R";
        datastreamDescription = "�蝝舐����"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Rain";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="millimeter";
        unitOfMeasurementSymbol ="mm";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Millimetre";
        // Datastream Sensor
//        sensorName = "O3 sensor";
//        sensorDescription = "O3 sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "O3 sensor";
        // Datastream ObservedProperty
        observedPropertyName = "24R";
        observedPropertyDefinition = "";
        observedPropertyDescription = "24R �蝝舐����";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 8 - H_FX 撠����憭折憸券◢��(m/s)
        datastreamName = "H_FX";
        datastreamDescription = "撠����憭折憸券◢��"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Wind_speed";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="metre per second";
        unitOfMeasurementSymbol ="m/s";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Metre_per_second";
        // Datastream Sensor
//        sensorName = "NO sensor";
//        sensorDescription = "NO sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "NO sensor";
        // Datastream ObservedProperty
        observedPropertyName = "H_FX";
        observedPropertyDefinition = "";
        observedPropertyDescription = "H_FX 撠����憭折憸券◢��";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 9 - H_XD 撠����憭折憸券◢��� (degree)
        datastreamName = "H_XD";
        datastreamDescription = "撠����憭折憸券◢���"; 
        datastreamObservationType = "";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="azimuth";
        unitOfMeasurementSymbol ="degrees";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Azimuth";
        // Datastream Sensor
//        sensorName = "NO2 sensor";
//        sensorDescription = "NO2 sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "NO2 sensor";
        // Datastream ObservedProperty
        observedPropertyName = "H_XD";
        observedPropertyDefinition = "";
        observedPropertyDescription = "H_XD 撠����憭折憸券◢���";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 10 - H_FXT 撠����憭折憸函�����(撠����hmm)
        datastreamName = "H_FXT";
        datastreamDescription = "撠����憭折憸函�����"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Wind_direction";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="hours and minutes";
        unitOfMeasurementSymbol ="hhmm";
        unitOfMeasurementDefinition ="";
        // Datastream Sensor
//        sensorName = "NOx sensor";
//        sensorDescription = "NOx sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "NOx sensor";
        // Datastream ObservedProperty
        observedPropertyName = "H_FX";
        observedPropertyDefinition = "";
        observedPropertyDescription = "H_FX 撠����憭折憸函�����";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 11 - H_F10 ����憭�10���像��◢��
        datastreamName = "H_F10";
        datastreamDescription = "����憭�10���像��◢��"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Wind_speed";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="metre per second";
        unitOfMeasurementSymbol ="m/s";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Metre_per_second";
        // Datastream Sensor
//        sensorName = "SO2 sensor";
//        sensorDescription = "SO2 sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "SO2 sensor";
        // Datastream ObservedProperty
        observedPropertyName = "H_F10";
        observedPropertyDefinition = "";
        observedPropertyDescription = "H_F10 ����憭�10���像��◢��";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 12 - H_10D ����憭�10���像��◢���
        datastreamName = "H_10D";
        datastreamDescription = "����憭�10���像��◢���"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Wind_direction";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="azimuth";
        unitOfMeasurementSymbol ="degrees";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Azimuth";
        // Datastream Sensor
//        sensorName = "PM10 sensor";
//        sensorDescription = "PM10 sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "PM10 sensor";
        // Datastream ObservedProperty
        observedPropertyName = "H_10D";
        observedPropertyDefinition = "";
        observedPropertyDescription = "H_10D ����憭�10���像��◢���";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 13 - H_F10T ����憭�10���像��◢������(撠����hmm)
        datastreamName = "H_F10T";
        datastreamDescription = "����憭�10���像��◢������"; 
        datastreamObservationType = "";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="hours and minutes";
        unitOfMeasurementSymbol ="hhmm";
        unitOfMeasurementDefinition ="";
        // Datastream Sensor
//        sensorName = "PM10 sensor";
//        sensorDescription = "PM10 sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "PM10 sensor";
        // Datastream ObservedProperty
        observedPropertyName = "H_F10T";
        observedPropertyDefinition = "";
        observedPropertyDescription = "H_F10T ����憭�10���像��◢������";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 14 - H_UVI 撠�換憭��
        datastreamName = "H_UVI";
        datastreamDescription = "撠�換憭��"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Ultraviolet";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="";
        unitOfMeasurementSymbol ="";
        unitOfMeasurementDefinition ="";
        // Datastream Sensor
//        sensorName = "PM2.5 sensor";
//        sensorDescription = "PM2.5 sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "PM2.5 sensor";
        // Datastream ObservedProperty
        observedPropertyName = "H_UVI";
        observedPropertyDefinition = "";
        observedPropertyDescription = "H_UVI 撠�換憭��";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 15 - D_TX ����擃澈摨�(������)
        datastreamName = "D_TX";
        datastreamDescription = "����擃澈摨�"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Temperature";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="Celsius";
        unitOfMeasurementSymbol ="���";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Celsius";
        // Datastream Sensor
//        sensorName = "PM2.5 sensor";
//        sensorDescription = "PM2.5 sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "PM2.5 sensor";
        // Datastream ObservedProperty
        observedPropertyName = "D_TX";
        observedPropertyDefinition = "";
        observedPropertyDescription = "D_TX ����擃澈摨�";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
                
        // datastream 16 - D_TXT ����擃澈摨衣�����(撠����hmm)
        datastreamName = "D_TXT";
        datastreamDescription = "����擃澈摨衣�����"; 
        datastreamObservationType = "";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="hours and minutes";
        unitOfMeasurementSymbol ="hhmm";
        unitOfMeasurementDefinition ="";
        // Datastream Sensor
//        sensorName = "WindSpeed sensor";
//        sensorDescription = "WindSpeed sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "WindSpeed sensor";
        // Datastream ObservedProperty
        observedPropertyName = "D_TXT";
        observedPropertyDefinition = "";
        observedPropertyDescription = "D_TXT ����擃澈摨衣�����";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 17 - D_TN ����雿澈摨�(������)
        datastreamName = "D_TN";
        datastreamDescription = "����雿澈摨�"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Temperature";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="Celsius";
        unitOfMeasurementSymbol ="���";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Celsius";
        // Datastream Sensor
//        sensorName = "WindDirection sensor";
//        sensorDescription = "WindDirection sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "WindDirection sensor";
        // Datastream ObservedProperty
        observedPropertyName = "D_TN";
        observedPropertyDefinition = "";
        observedPropertyDescription = "D_TN ����雿澈摨�";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        // datastream 18 - D_TNT ����雿澈摨衣�����(撠����hmm)
        datastreamName = "D_TNT";
        datastreamDescription = "����雿澈摨衣�����"; 
        datastreamObservationType = "";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="hours and minutes";
        unitOfMeasurementSymbol ="hhmm";
        unitOfMeasurementDefinition ="";
        // Datastream Sensor
//        sensorName = "WindSpeed sensor";
//        sensorDescription = "WindSpeed sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "WindSpeed sensor";
        // Datastream ObservedProperty
        observedPropertyName = "D_TNT";
        observedPropertyDefinition = "";
        observedPropertyDescription = "D_TNT ����雿澈摨衣�����";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        
        // datastream 19 - D_TS ����(撠��r)
        datastreamName = "D_TS";
        datastreamDescription = "����"; 
        datastreamObservationType = "";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="hours";
        unitOfMeasurementSymbol ="hr";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Hour";
        // Datastream Sensor
//        sensorName = "WindSpeed sensor";
//        sensorDescription = "WindSpeed sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "WindSpeed sensor";
        // Datastream ObservedProperty
        observedPropertyName = "D_TS";
        observedPropertyDefinition = "";
        observedPropertyDescription = "D_TS ����";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        
        // datastream 20 - H_VIS ���暺��閬漲(����m)
        datastreamName = "H_VIS";
        datastreamDescription = "���暺��閬漲"; 
        datastreamObservationType = "https://en.wikipedia.org/wiki/Visibility";
        // Datastream unitOfMeasurement
        unitOfMeasurementName ="Kilometre";
        unitOfMeasurementSymbol ="km";
        unitOfMeasurementDefinition ="https://en.wikipedia.org/wiki/Kilometre";
        // Datastream Sensor
//        sensorName = "WindSpeed sensor";
//        sensorDescription = "WindSpeed sensor";
//        sensorEncodingType = "text/plain";
//        sensorMetadata = "WindSpeed sensor";
        // Datastream ObservedProperty
        observedPropertyName = "H_VIS";
        observedPropertyDefinition = "";
        observedPropertyDescription = "H_VIS ���暺��閬漲";

        uom = new UnitOfMeasurement(unitOfMeasurementName, unitOfMeasurementSymbol, unitOfMeasurementDefinition);
//        sensor = new Sensor(sensorName, sensorDescription, sensorEncodingType, sensorMetadata);
        observedProperty = new ObservedProperty(observedPropertyName, observedPropertyDefinition, observedPropertyDescription);
        datastream = new Datastream(datastreamName, datastreamDescription, datastreamObservationType, uom, sensor, observedProperty);
        
        Datastreams.put(datastream.getJSONObject());
        
        thingObj.put("Datastreams", Datastreams);
        
        JSONArray Locations = new JSONArray();
        Location location = new Location("撅�撅祆除鞊∠��-"+this.County, "撅�撅祆除鞊∠��-"+this.station_name, "application/vnd.geo+json", "Point", this.lon, this.lat);

       // Location addressLocation = new Location("蝛箸除��釭皜祉��-"+this.SiteName, "蝛箸除��釭皜祉��-"+this.SiteName, "address", "Address", this.SiteAddress);
//        Location otherLocation = new Location("蝛箸除��釭皜祉��-"+this.SiteName, "蝛箸除��釭皜祉��-"+this.SiteName, "plain/text", "City", this.City, "address", "Address", this.Address);
        
        Locations.put(location.getJSONObject());
//        Locations.put(otherLocation.getCityObject());
        //Locations.put(addressLocation.getAddressObject());

        thingObj.put("Locations", Locations);
        
        String data = thingObj.toString();
        
        return data;
    }
    
}
