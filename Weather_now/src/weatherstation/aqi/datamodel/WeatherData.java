package weatherstation.aqi.datamodel;

public class WeatherData {

	public String stationId = null;
	public String locationName = null;
	private String obsTime = null;
	private String elementName = null;
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
	
	public String getObsTime() {
		return obsTime;
	}

	public void setObsTime(String obsTime) {
		this.obsTime = obsTime;
	}

	public double getELEV() {
		return ELEV;
	}

	public void setELEV(double eLEV) {
		ELEV = eLEV;
	}

	public double getWDIR() {
		return WDIR;
	}

	public void setWDIR(double wDIR) {
		WDIR = wDIR;
	}

	public double getWDSD() {
		return WDSD;
	}

	public void setWDSD(double wDSD) {
		WDSD = wDSD;
	}

	public double getTEMP() {
		return TEMP;
	}

	public void setTEMP(double tEMP) {
		TEMP = tEMP;
	}

	public double getHUMD() {
		return HUMD;
	}

	public void setHUMD(double hUMD) {
		HUMD = hUMD;
	}

	public double getPRES() {
		return PRES;
	}

	public void setPRES(double pRES) {
		PRES = pRES;
	}

	public double getH_24R() {
		return H_24R;
	}

	public void setH_24R(double h_24R) {
		H_24R = h_24R;
	}


	public double getH_FX() {
		return H_FX;
	}

	public void setH_FX(double h_FX) {
		H_FX = h_FX;
	}

	public double getH_XD() {
		return H_XD;
	}

	public void setH_XD(double h_XD) {
		H_XD = h_XD;
	}

	public String getH_FXT() {
		return H_FXT;
	}

	public void setH_FXT(String h_FXT) {
		H_FXT = h_FXT;
	}

	public double getD_TX() {
		return D_TX;
	}

	public void setD_TX(double d_TX) {
		D_TX = d_TX;
	}

	public String getD_TXT() {
		return D_TXT;
	}

	public void setD_TXT(String d_TXT) {
		D_TXT = d_TXT;
	}



	public double getD_TN() {
		return D_TN;
	}

	public void setD_TN(double d_TN) {
		D_TN = d_TN;
	}



	public String getD_TNT() {
		return D_TNT;
	}

	public void setD_TNT(String d_TNT) {
		D_TNT = d_TNT;
	}
	
	public String getVIS() {
		return VIS;
	}

	public void setVIS(String VIS) {
		VIS = VIS;
	}
	
	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getWeather() {
		return Weather;
	}

	public void setWeather(String weather) {
		Weather = weather;
	}
	
	public double getH_F10() {
		return H_F10;
	}

	public void setH_F10(double h_F10) {
		H_F10 = h_F10;
	}

	public double getH_10D() {
		return H_10D;
	}

	public void setH_10D(double h_10d) {
		H_10D = h_10d;
	}

	public String getH_F10T() {
		return H_F10T;
	}

	public void setH_F10T(String h_F10T) {
		H_F10T = h_F10T;
	}

	public double getH_UVI() {
		return H_UVI;
	}

	public void setH_UVI(double h_UVI) {
		H_UVI = h_UVI;
	}
	
	

	public WeatherData(String stationId,String locationName, String obsTime, String WDIR, String WDSD, String TEMP, String HUMD, String PRES,
		String H_24R, String H_FX, String H_XD, String H_FXT, String H_F10, String H_10D, String H_F10T, String H_UVI, String D_TX, String D_TXT, String D_TN, String D_TNT, String VIS, String weather ) 
	{
		this.obsTime = obsTime;
		this.stationId = stationId;
		this.locationName = locationName;
		this.WDIR = Double.valueOf(WDIR);
		this.WDSD = Double.valueOf(WDSD);
		this.TEMP = Double.valueOf(TEMP);
		this.HUMD = Double.valueOf(HUMD);
		this.PRES = Double.valueOf(PRES);
		this.H_24R = Double.valueOf(H_24R);
		this.H_FX = Double.valueOf(H_FX);
		this.H_XD = Double.valueOf(H_XD);
		this.H_F10 = Double.valueOf(H_F10);
		this.H_10D = Double.valueOf(H_10D);
		String ori = obsTime.substring(0,obsTime.indexOf("T"));
		
		if(H_F10T.equalsIgnoreCase("-99")) {
			this.H_F10T = this.obsTime;
		}
		else {		
			String NEW_H_F10T = ori +"T"+ H_F10T.substring(0, 2) +":"+ H_F10T.substring(2, H_F10T.length()) + ":00";
			this.H_F10T = NEW_H_F10T;
		}
		
		if(H_FXT.equalsIgnoreCase("-99")) {
			this.H_FXT = this.obsTime;
		}
		else {
			String NEW_H_FXT = ori +"T"+ H_FXT.substring(0, 2) +":"+ H_FXT.substring(2, H_FXT.length()) + ":00";
			this.H_FXT = NEW_H_FXT;
		}
		
		if(D_TNT.equalsIgnoreCase("-99")) {
			this.D_TNT = this.obsTime;
		}
		else {
			String NEW_D_TNT = ori +"T"+ D_TNT.substring(0, 2) +":"+ D_TNT.substring(2, D_TNT.length())+ ":00";
			this.D_TNT = NEW_D_TNT;
		}
		
		if(D_TXT.equalsIgnoreCase("-99")) {
			this.D_TXT = this.obsTime;
		}
		else {
			String NEW_D_TXT = ori +"T"+ D_TXT.substring(0, 2) +":"+ D_TXT.substring(2, D_TXT.length())+ ":00";
			this.D_TXT = NEW_D_TXT;
		}
		this.D_TX = Double.valueOf(D_TX);	
		this.D_TN = Double.valueOf(D_TN);		
		this.VIS = VIS;
		this.Weather = weather;
		
	}

	}

