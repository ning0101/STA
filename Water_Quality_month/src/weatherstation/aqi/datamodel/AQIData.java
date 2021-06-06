/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.aqi.datamodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import weatherstation.datamodel.Observation;
import org.json.JSONException;
import org.w3c.dom.Element;

/**
 *
 * @author ning0101
 */
public class AQIData {
	private String station_id = null;
	public String getStation_id() {
		return station_id;
	}

	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}

	private String obsTime = null;
	private double ELEV = Double.MAX_VALUE;
	private double WDIR = Double.MAX_VALUE;
	private double WDSD = Double.MAX_VALUE;
	private double TEMP = Double.MAX_VALUE;
	private double HUMD = Double.MAX_VALUE;
	private double PRES = Double.MAX_VALUE;
	private double R24 = Double.MAX_VALUE;
	private double H_FX = Double.MAX_VALUE;
	private double H_XD = Double.MAX_VALUE;
	private double H_FXT = Double.MAX_VALUE;
	private double H_F10 = Double.MAX_VALUE;
	private double H_10D = Double.MAX_VALUE;
	private double H_F10T = Double.MAX_VALUE;
	private double H_UVI = Double.MAX_VALUE;
	private double D_TX = Double.MAX_VALUE;
	private double D_TXT = Double.MAX_VALUE;
	private double D_TN = Double.MAX_VALUE;
	private double D_TNT = Double.MAX_VALUE;
	private double D_TS = Double.MAX_VALUE;
	private double H_VIS = Double.MAX_VALUE;
    
    public AQIData(metro_20181102 metro_20181102) throws ParseException {
    				this.station_id = metro_20181102.getStation_id();
    				SimpleDateFormat dateFormatter = null;
    				if(metro_20181102.getObsTime().length()>16) {
    					dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    				}else {    				
    					dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    				}
    			    Date obsTime = dateFormatter.parse(metro_20181102.getObsTime());
    			    SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z");
    				this.obsTime = timeFormatter.format(obsTime);
    				this.ELEV = metro_20181102.getELEV();
    				this.WDIR = metro_20181102.getWDIR();
    				this.WDSD = metro_20181102.getWDSD();
    				this.TEMP = metro_20181102.getTEMP();
    				this.HUMD = metro_20181102.getHUMD();
    				this.PRES = metro_20181102.getPRES();
    				this.R24 = metro_20181102.getR24();
    				this.H_FX = metro_20181102.getH_FX();
    				this.H_XD = metro_20181102.getH_XD();
    				this.H_FXT = metro_20181102.getH_FXT();
    				this.H_F10 = metro_20181102.getH_F10();
    				this.H_10D = metro_20181102.getH_10D();
    				this.H_F10T = metro_20181102.getH_F10T();
    				this.H_UVI = metro_20181102.getH_UVI();
    				this.D_TX = metro_20181102.getD_TX();
    				this.D_TXT = metro_20181102.getD_TXT();
    				this.D_TN = metro_20181102.getD_TN();
    				this.D_TNT = metro_20181102.getD_TNT();
    				this.D_TS = metro_20181102.getD_TS();
    				this.H_VIS = metro_20181102.getH_VIS();
    }
    
    public String getObsTime() {
		return obsTime;
	}

	public void setObsTime(String obsTime) {
		this.obsTime = obsTime;
	}

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
            case "R24":
                if(this.R24!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.R24);
                break;
            case "H_FX":
                if(this.H_FX!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_FX);
                break;
            case "H_XD":
                if(this.H_XD!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_XD);
                break;
            case "H_FXT":
                if(this.H_FXT!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_FXT);
                break;
            case "H_F10":
                if(this.H_F10!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_F10);
                break;
            case "H_10D":
                if(this.H_10D!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_10D);
                break;
            case "H_F10T":
                if(this.H_F10T!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_F10T);
                break;
            case "H_UVI":
                if(this.H_UVI!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_UVI);
                break;
            case "D_TX":
                if(this.D_TX!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.D_TX);
                break;
            case "D_TXT":
                if(this.D_TXT!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.D_TXT);
                break;
            case "D_TN":
                if(this.D_TN!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.D_TN);
                break;
            case "D_TNT":
                if(this.D_TNT!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.D_TNT);
                break;
            case "D_TS":
                if(this.D_TS!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.D_TS);
                break;
            case "H_VIS":
                if(this.H_VIS!=Double.MAX_VALUE)
                    observation = new Observation(this.obsTime, this.H_VIS);
                break;
        }
        
        if(observation!=null){
            ObservationEntityString = observation.getJSONString();
        }
        
        return ObservationEntityString;
    }
}
