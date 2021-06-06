package weatherstation.aqi.datamodel;

public class WeatherStation {
	private String County = null;
	private String DamName = null;
	private String SiteEngName = null;
	private int SiteId = Integer.MAX_VALUE;
	private String SiteName = null;
	private String Township = null;
	private double TWD97Lat = Double.MAX_VALUE;
	private double TWD97Lon = Double.MAX_VALUE;
	private double TWD97TM2X = Double.MAX_VALUE;
	private double TWD97TM2Y = Double.MAX_VALUE;

	public String getCounty() {
		return County;
	}

	public void setCounty(String county) {
		this.County = county;
	}

	public String getDamName() {
		return DamName;
	}

	public void setDamName(String damName) {
		this.DamName = damName;
	}

	public String getSiteEngName() {
		return SiteEngName;
	}

	public void setSiteEngName(String siteEngName) {
		this.SiteEngName = siteEngName;
	}

	public int getSiteId() {
		return SiteId;
	}

	public void setSiteId(int siteId) {
		this.SiteId = siteId;
	}

	public String getSiteName() {
		return SiteName;
	}

	public void setSiteName(String siteName) {
		this.SiteName = siteName;
	}

	public String getTownship() {
		return Township;
	}

	public void setTownShip(String township) {
		this.Township = township;
	}

	public double getTWD97Lat() {
		return TWD97Lat;
	}

	public void setTWD97Lat(double tWD97Lat) {
		this.TWD97Lat = tWD97Lat;
	}

	public double getTWD97Lon() {
		return TWD97Lon;
	}

	public void setTWD97Lon(double tWD97Lon) {
		this.TWD97Lon = tWD97Lon;
	}

	public double getTWD97TM2X() {
		return TWD97TM2X;
	}

	public void setTWD97TM2X(double tWD97TM2X) {
		this.TWD97TM2X = tWD97TM2X;
	}

	public double getTWD97TM2Y() {
		return TWD97TM2Y;
	}

	public void setTWD97TM2Y(double tWD97TM2Y) {
		this.TWD97TM2Y = tWD97TM2Y;
	}
	
	
	public WeatherStation(
			String County, String DamName, String SiteEngName, int SiteId, String SiteName, String Township, double TWD97Lat, double TWD97Lon, double TWD97TM2X, double TWD97TM2Y) {
		this.County = County;
		this.DamName = DamName;
		this.SiteEngName = SiteEngName;
		this.SiteId = SiteId;
		this.SiteName = SiteName;
		this.Township = Township;
		this.TWD97Lat = TWD97Lat;
		this.TWD97Lon = TWD97Lon;
		this.TWD97TM2X = TWD97TM2X;
		this.TWD97TM2Y = TWD97TM2Y;
	}




}