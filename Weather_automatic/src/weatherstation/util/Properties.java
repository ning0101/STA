package weatherstation.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author alec
 */
public class Properties {
    private final static Properties instance = new Properties();
    
    private final static String PROPERTIES_FILEPATH = "./properties/properties.json";
    
//    private final static String USER_AGENT = "Mozilla/5.0";
    
    public static long FEEDING_FREQUENCY = 30*60*1000;
    
    public static String URL_STA = null;
//    public final static String URL_STA_EPA_AQI = "https://sta.ci.taiwan.gov.tw/STA_AirQuality/v1.0";
//    public static String URL_STA = "http://cgis-dev.csrsr.ncu.edu.tw:8080/STA_AirQuality_v03/v1.0";
    
    public static String URL_EPA_AQI_STATION = null;
    public static String URL_EPA_AQI_DATA = null;
//    public static String URL_EPA_AQI_STATION = "http://opendata.epa.gov.tw/ws/Data/AQXSite/?$orderby=SiteName&$skip=0&$top=1000&format=xml";
//    public static String URL_EPA_AQI_DATA = "http://opendata2.epa.gov.tw/AQI.xml";
    
    public static String SENSOR_URN_PREFIX = null;
//    public static String SENSOR_URN_PREFIX = "urn:epa:station:aqi:";
    
    public Properties(){
        
    }
    
    public static Properties getInstance(){
        return instance;
    }
    
    public void refresh() throws IOException{
        String jsonString = readFile(Properties.PROPERTIES_FILEPATH);
        JSONObject jsonObj = new JSONObject(jsonString);
        FEEDING_FREQUENCY = jsonObj.getLong("FEEDING_FREQUENCY");
        URL_STA = jsonObj.getString("URL_STA");
        URL_EPA_AQI_STATION = jsonObj.getString("URL_EPA_AQI_STATION");
        URL_EPA_AQI_DATA = jsonObj.getString("URL_EPA_AQI_DATA");
        SENSOR_URN_PREFIX = jsonObj.getString("SENSOR_URN_PREFIX");
    }
    
    private static String readFile(String filePath) throws FileNotFoundException, IOException{
        StringBuilder sb = new StringBuilder();

        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        while (br.ready()) {
            sb.append(br.readLine()+"\n");
        }
        fr.close();

        return sb.toString();
    }
}
