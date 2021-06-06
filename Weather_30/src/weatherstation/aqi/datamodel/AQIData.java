/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.aqi.datamodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import weatherstation.datamodel.Observation;
/**
 *
 * @author ning0101
 */
public class AQIData {
	private String stationID = null;
	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	
	private String stationName = null;
	public String getStationName() {
		return stationName;
	}
	
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	private String dataTime = null;
	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	

//	private double WDIR = Double.MAX_VALUE;
//	private double WDSD = Double.MAX_VALUE;
//	private double TEMP = Double.MAX_VALUE;
//	private double HUMD = Double.MAX_VALUE;
//	private double HPA = Double.MAX_VALUE;
//	private double RAIN = Double.MAX_VALUE;
	private double stationPressure = Double.MAX_VALUE;    
	private double temperature = Double.MAX_VALUE;   
	private double relativeHumidity = Double.MAX_VALUE;  
	private double windSpeed = Double.MAX_VALUE;  
	private String windDirectionDescription = null;  
	private double precipitation = Double.MAX_VALUE;
	private String sunshineDuration = null;

	
    
    public AQIData(WeatherData weather_data) throws ParseException {
    	this.stationID = weather_data.getStationID();  	
    	this.stationName = weather_data.getStationName();
    	LocalDateTime date = LocalDateTime.parse(weather_data.getDataTime(),DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    	this.dataTime = weather_data.getDataTime();
		this.stationPressure = weather_data.getStationPressure();
		this.temperature = weather_data.getTemperature();
		this.relativeHumidity = weather_data.getRelativeHumidity();
		this.windSpeed = weather_data.getWindSpeed();
		this.windDirectionDescription = weather_data.getWindDirectionDescription();
		this.precipitation = weather_data.getPrecipitation();
		this.sunshineDuration = weather_data.getSunshineDuration();
		// obstime
}


//    				switch(weather_data.getItemEngName()) {
//    					case "pH":
//    						this.pH = weather_data.getItemValue();
//    						break;
//    					case "Dissolved Oxygen":
//    						this.DO = weather_data.getItemValue();
//    						break;
//    					case "Chlorophyl-A":
//    						this.ChlA = weather_data.getItemValue();
//    						break;
//    					case "Transparency":
//    						this.SD = weather_data.getItemValue();
//    						break;
//    					case "Dissolved Oxygen Saturation":
//    						this.DOS = weather_data.getItemValue();
//    						break;
//    					case "Total-Phosphate":
//    						this.TP = weather_data.getItemValue();
//    						break;
//    					case "Total Organic Carbon":
//    						this.TOC = weather_data.getItemValue();
//    						break;
//    					case "NH3-N":
//    						this.NH3N = weather_data.getItemValue();
//    						break;
//    					case "Water Temperature":
//    						this.WT = weather_data.getItemValue();
//    						break;
//    					case "Carlson Trophic State Index":
//    						this.CTSI = weather_data.getItemValue();
//    						break;
//    					case "Chemical Oxygen Demand":
//    						this.COD = weather_data.getItemValue();
//    						break;
//    					case "Turbidity":
//    						this.TB = weather_data.getItemValue();
//    						break;
//    					case "Conductivity":
//    						this.EC = weather_data.getItemValue();
//    						break;		
//    				}					 						
//    }
    
    			
	public String getSTAObservationEntity(String datastreamName) throws ParseException{
        String ObservationEntityString = null;
        Observation observation = null;
        
        switch (datastreamName) {
            case "測站氣壓":
                if(this.stationPressure!=Double.MAX_VALUE)
                    observation = new Observation(this.dataTime, this.stationPressure);
                break;
            case "測站溫度":
                if(this.temperature!=Double.MAX_VALUE)
                    observation = new Observation(this.dataTime, this.temperature);
                break;
            case "相對濕度":
                if(this.relativeHumidity!=Double.MAX_VALUE)
                    observation = new Observation(this.dataTime, this.relativeHumidity);
                break;
            case "測站風速":
                if(this.windSpeed!=Double.MAX_VALUE)
                    observation = new Observation(this.dataTime, this.windSpeed);
                break;
            case "測站風向描述":
                if(this.windDirectionDescription!= null)
                    observation = new Observation(this.dataTime, this.windDirectionDescription);
                break;
            case "降水量":
                if(this.precipitation!=Double.MAX_VALUE)
                    observation = new Observation(this.dataTime, this.precipitation);
                break;
            case "測站日照時數":
                if(this.sunshineDuration!= null)
                    observation = new Observation(this.dataTime, this.sunshineDuration);
                break;
        }
        
        if(observation!=null){
            ObservationEntityString = observation.getJSONString();
        }
        
        return ObservationEntityString;
    }


}
