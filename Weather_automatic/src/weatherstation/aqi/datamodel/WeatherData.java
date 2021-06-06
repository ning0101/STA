package weatherstation.aqi.datamodel;

public class WeatherData {

	private String stationId = null;
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
	private double H_FXT = Double.MAX_VALUE;
	private double D_TX = Double.MAX_VALUE;
	private String D_TXT = null;
	private double D_TN = Double.MAX_VALUE;
	private String D_TNT = null;
	private String locationName = null;

	
	public String getStationId() {
		return stationId;
	}
	
	public void setStationId(String stationId) {
		this.stationId = stationId;
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

	public double getH_FXT() {
		return H_FXT;
	}

	public void setH_FXT(double h_FXT) {
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


	public WeatherData(String stationId, String locationName, String obsTime, String WDIR, String WDSD, String TEMP, String HUMD, String PRES,
		String H_24R, String H_FX, String H_XD, String H_FXT, String D_TX, String D_TXT, String D_TN, String D_TNT ) 
	{
		this.obsTime = obsTime;
		this.stationId = stationId;
		this.WDIR = Double.valueOf(WDIR);
		this.WDSD = Double.valueOf(WDSD);
		this.TEMP = Double.valueOf(TEMP);
		this.HUMD = Double.valueOf(HUMD);
		this.PRES = Double.valueOf(PRES);
		this.H_24R = Double.valueOf(H_24R);
		this.H_FX = Double.valueOf(H_FX);
		this.H_XD = Double.valueOf(H_XD);
		this.H_FXT = Double.valueOf(H_FXT);
		this.D_TX = Double.valueOf(D_TX);
		this.D_TXT = D_TXT;
		this.D_TN = Double.valueOf(D_TN);
		this.D_TNT = D_TNT;
		this.locationName = locationName;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	}

