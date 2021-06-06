package weatherstation.aqi.datamodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherData {

	private String stationID = null;
	private String stationName = null;
	private String dataTime = null;
	private double stationPressure = Double.MAX_VALUE;    
	private double temperature = Double.MAX_VALUE;   
	private double relativeHumidity = Double.MAX_VALUE;  
	private double windSpeed = Double.MAX_VALUE;  
	private String windDirectionDescription = null;  
	private double precipitation = Double.MAX_VALUE;
	private String sunshineDuration = null;
	

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

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public double getStationPressure() {
		return stationPressure;
	}

	public void setStationPressure(double stationPressure) {
		this.stationPressure = stationPressure;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setRelativeHumidity(double relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindDirectionDescription() {
		return windDirectionDescription;
	}

	public void setWindDirectionDescription(String windDirectionDescription) {
		this.windDirectionDescription = windDirectionDescription;
	}

	public double getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}

	public String getSunshineDuration() {
		return sunshineDuration;
	}

	public void setSunshineDuration(String sunshineDuration) {
		this.sunshineDuration = sunshineDuration;
	}

	public WeatherData(String stationID, String stationName, String dataTime, String stationPressure, String temperature,
		   String relativeHumidity,	String windSpeed, String windDirectionDescription, String precipitation, String sunshineDuration) 
	{
		this.stationID = stationID;
		this.stationName = stationName;
		this.dataTime = dataTime;
		String hour = this.dataTime.substring(this.dataTime.indexOf("T")+1, this.dataTime.indexOf(":"));
		if(hour.equalsIgnoreCase("24")) {
			String temp = this.dataTime.replaceFirst("T24:", "T00:");
			this.dataTime = LocalDateTime.parse(temp,DateTimeFormatter.ISO_OFFSET_DATE_TIME).plusDays(1).toString()+":00+08:00";
		}
		try {
			this.stationPressure = Double.valueOf(stationPressure);
		}catch(Exception e) {
			this.stationPressure = 0.0;
		}
		try {
			this.temperature = Double.valueOf(stationPressure);
		}catch(Exception e) {
			this.temperature = 0.0;
		}
		try {
			this.relativeHumidity = Double.valueOf(stationPressure);
		}catch(Exception e) {
			this.relativeHumidity = 0.0;
		}
		try {
			this.windSpeed = Double.valueOf(stationPressure);
		}catch(Exception e) {
			this.windSpeed = 0.0;
		}
		try {
			this.precipitation = Double.valueOf(stationPressure);
		}catch(Exception e) {
			this.precipitation = 0.0;
		}		
		this.windDirectionDescription = windDirectionDescription;
		if(sunshineDuration != null) {
			this.sunshineDuration = sunshineDuration;
		}
	}

	
}

