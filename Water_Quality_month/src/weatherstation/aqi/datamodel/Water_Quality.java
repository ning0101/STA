package weatherstation.aqi.datamodel;

public class Water_Quality {

		private String County = null;
		private String DamName = null;
		private String ItemEngAbbreviation = null;
		private String ItemEngName = null;
		private String ItemName = null;
		private String ItemUnit = null;
		private String ItemValue = null;
		private int SampleDate = (int) Double.MAX_VALUE;
		private String SampleDepth = null;
		private String SampleLayer = null;
		private String SiteEngName = null;
		private double SiteId = Double.MAX_VALUE;
		private String SiteName = null;
		private String Township = null;
		private double TWD97Lat = Double.MAX_VALUE;
		private double TWD97Lon = Double.MAX_VALUE;
		private double TWD97TM2X = Double.MAX_VALUE;
		private double TWD97TM2Y = Double.MAX_VALUE;

		





		public Water_Quality(
				String County, String DamName, String ItemEngAbbreviation,
				String ItemEngName, String ItemName, String ItemUnit, String ItemValue,
				int SampleDate, String SampleDepth, String SampleLayer, 
				String SiteEngName,double SiteId, String SiteName, String Township, 
				double TWD97Lat,double TWD97Lon, double TWD97TM2X, double TWD97TM2Y 
	 ) {
			this.County = County;
			this.DamName = DamName;
			this.ItemEngAbbreviation = ItemEngAbbreviation;
			this.ItemEngName = ItemEngName;
			this.ItemName = ItemName;
			this.ItemUnit = ItemUnit;
			this.ItemValue = ItemValue;
			this.SampleDate = SampleDate;
			this.SampleDepth = SampleDepth;
			this.SampleLayer = SampleLayer;
			this.SiteEngName = SiteEngName;
			this.SiteId = SiteId;
			this.SiteName = SiteName;
			this.Township = Township;
			this.TWD97Lat = TWD97Lat;
			this.TWD97Lon = TWD97Lon;

				
		}

		public Water_Quality(String string, String string2, double parseDouble, double parseDouble2, double parseDouble3,
				double parseDouble4, double parseDouble5, double parseDouble6, String string3, int parseInt) {
			// TODO Auto-generated constructor stub
		}

		public String getCounty() {
			return County;
		}

		public void setCounty(String county) {
			County = county;
		}

		public String getDamName() {
			return DamName;
		}

		public void setDamName(String damName) {
			DamName = damName;
		}

		public String getItemEngAbbreviation() {
			return ItemEngAbbreviation;
		}

		public void setItemEngAbbreviation(String itemEngAbbreviation) {
			ItemEngAbbreviation = itemEngAbbreviation;
		}

		public String getItemEngName() {
			return ItemEngName;
		}

		public void setItemEngName(String itemEngName) {
			ItemEngName = itemEngName;
		}

		public String getItemName() {
			return ItemName;
		}

		public void setItemName(String itemName) {
			ItemName = itemName;
		}

		public String getItemUnit() {
			return ItemUnit;
		}

		public void setItemUnit(String itemUnit) {
			ItemUnit = itemUnit;
		}

		public String getItemValue() {
			return ItemValue;
		}

		public void setItemValue(String itemValue) {
			ItemValue = itemValue;
		}

		public int getSampleDate() {
			return SampleDate;
		}

		public void setSampleDate(int sampleDate) {
			SampleDate = sampleDate;
		}

		public String getSampleDepth() {
			return SampleDepth;
		}

		public void setSampleDepth(String sampleDepth) {
			SampleDepth = sampleDepth;
		}

		public String getSampleLayer() {
			return SampleLayer;
		}

		public void setSampleLayer(String sampleLayer) {
			SampleLayer = sampleLayer;
		}

		public String getSiteEngName() {
			return SiteEngName;
		}

		public void setSiteEngName(String siteEngName) {
			SiteEngName = siteEngName;
		}

		public double getSiteId() {
			return SiteId;
		}

		public void setSiteId(double siteId) {
			SiteId = siteId;
		}

		public String getSiteName() {
			return SiteName;
		}

		public void setSiteName(String siteName) {
			SiteName = siteName;
		}

		public String getTownship() {
			return Township;
		}

		public void setTownship(String township) {
			Township = township;
		}

		public double getTWD97Lat() {
			return TWD97Lat;
		}

		public void setTWD97Lat(double tWD97Lat) {
			TWD97Lat = tWD97Lat;
		}

		public double getTWD97Lon() {
			return TWD97Lon;
		}

		public void setTWD97Lon(double tWD97Lon) {
			TWD97Lon = tWD97Lon;
		}

		public double getTWD97TM2X() {
			return TWD97TM2X;
		}

		public void setTWD97TM2X(double tWD97TM2X) {
			TWD97TM2X = tWD97TM2X;
		}

		public double getTWD97TM2Y() {
			return TWD97TM2Y;
		}

		public void setTWD97TM2Y(double tWD97TM2Y) {
			TWD97TM2Y = tWD97TM2Y;
		}


	}
