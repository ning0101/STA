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
	private String stationId = null;
	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	public String locationName = null;
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	private String obsTime = null;
	public String getObsTime() {
		return obsTime;
	}

	public void setObsTime(String obsTime) {
		this.obsTime = obsTime;
	}

	private double ELEV = Double.MAX_VALUE;
	private double WDIR = Double.MAX_VALUE;
	private double WDSD = Double.MAX_VALUE;
	private double TEMP = Double.MAX_VALUE;
	private double HUMD = Double.MAX_VALUE;
	private double PRES = Double.MAX_VALUE;
	private double H_24R = Double.MAX_VALUE;
	private double H_FX = Double.MAX_VALUE;
	private double H_XD = Double.MAX_VALUE;
	private String H_FXT = null;
	private double H_F10 = Double.MAX_VALUE;
	private double H_10D = Double.MAX_VALUE;
	private String H_F10T = null;
	private double H_UVI = Double.MAX_VALUE;
	private double D_TX = Double.MAX_VALUE;
	private String D_TXT = null;
	private double D_TN = Double.MAX_VALUE;
	private String D_TNT = null;
	private String VIS = null;
	private String Weather = null;

    
    public AQIData(WeatherData weather_data) throws ParseException {
    	this.stationId = weather_data.getStationId();
    	this.locationName = weather_data.getLocationName();
    	LocalDateTime date = LocalDateTime.parse(weather_data.getObsTime(),DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    	this.obsTime = weather_data.getObsTime();
    	this.ELEV = weather_data.getELEV();
		this.WDIR = weather_data.getWDIR();
		this.WDSD = weather_data.getWDSD();
		this.TEMP = weather_data.getTEMP();
		this.HUMD = weather_data.getHUMD();
		this.PRES = weather_data.getPRES();
		this.H_24R = weather_data.getH_24R();
		this.H_FX = weather_data.getH_FX();
		this.H_XD = weather_data.getH_XD();
		this.H_FXT = weather_data.getH_FXT();
		this.H_F10 = weather_data.getH_F10();
		this.H_10D = weather_data.getH_10D();
		this.H_F10T = weather_data.getH_F10T();
		this.H_UVI = weather_data.getH_UVI();
		this.D_TX = weather_data.getD_TX();
		this.D_TXT = weather_data.getD_TXT();
		this.D_TN = weather_data.getD_TN();
		this.D_TNT = weather_data.getD_TNT();
		this.VIS = weather_data.getVIS();
		this.Weather = weather_data.getWeather();
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
            case "ELEV":
                if(this.ELEV!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.ELEV);
                break;
            case "WDIR":
                if(this.WDIR!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.WDIR);
                break;
            case "WDSD":
                if(this.WDSD!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.WDSD);
                break;
            case "TEMP":
                if(this.TEMP!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.TEMP);
                break;
            case "HUMD":
                if(this.HUMD!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.HUMD);
                break;
            case "PRES":
                if(this.PRES!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.PRES);
                break;
            case "24R":
                if(this.H_24R!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_24R);
                break;
            case "H_FX":
                if(this.H_FX!=Double.MAX_VALUE)
                    observation = new Observation(this.H_FXT, this.H_FX);
            case "H_XD":
                if(this.H_XD!=Double.MAX_VALUE)
                    observation = new Observation(this.H_FXT, this.H_XD);
                break;
            case "H_10D":
                if(this.H_10D!=Double.MAX_VALUE)
                    observation = new Observation(this.H_F10T, this.H_10D);
                break;
            case "H_F10":
                if(this.H_F10!=Double.MAX_VALUE)
                    observation = new Observation(this.H_F10T, this.H_F10);
                break;
            case "H_UVI":
                if(this.H_UVI!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_UVI);
                break;
//            case "H_FXT":
//                if(this.H_FXT!=Double.MAX_VALUE)
//                    observation = new Observation(this.obsTime, this.H_FXT);
//                break;
            case "D_TX":
                if(this.D_TX!=Double.MAX_VALUE)
                    observation = new Observation(this.D_TXT, this.D_TX);
                break;
//            case "D_TXT":
//                if(this.D_TXT!=null)
//                    observation = new Observation(this.obsTime, this.D_TXT);
//                break;
            case "D_TN":
                if(this.D_TN!=Double.MAX_VALUE)
                    observation = new Observation(this.D_TNT, this.D_TN);
                break;
//            case "D_TNT":
//                if(this.D_TNT!=null)
//                    observation = new Observation(this.obsTime, this.D_TNT);
//                break;
            case "VIS":
            	if(this.VIS!=null)
                    observation = new Observation(this.obsTime, this.VIS);
                break;
            case "Weather":
            	if(this.Weather!=null)
                    observation = new Observation(this.obsTime, this.Weather);
                break;
            	
        }
        
        if(observation!=null){
            ObservationEntityString = observation.getJSONString();
        }
        
        return ObservationEntityString;
    }

	public String getVIS() {
		return VIS;
	}

	public void setVIS(String vIS) {
		VIS = vIS;
	}

	public String getWeather() {
		return Weather;
	}

	public void setWeather(String weather) {
		Weather = weather;
	}
}
