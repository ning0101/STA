/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.datamodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import weatherstation.util.TimeInstance;
import org.json.JSONObject;

/**
 *
 * @author alec
 */
public class Observation {
    public String phenomenonTimeString = null;
    public JSONObject parameters = new JSONObject();
    public long phenomenonTime = Long.MAX_VALUE;
    public String result_String = null;
    public double result_double = Double.MAX_VALUE;
    public int result_int = Integer.MAX_VALUE;
    
    public Observation(String phenomenonTimeString, String result) throws ParseException{
    	LocalDateTime date = LocalDateTime.parse(phenomenonTimeString,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.phenomenonTime = date.toEpochSecond(ZoneOffset.UTC);
        this.phenomenonTime = date.toEpochSecond(ZoneOffset.UTC);
        this.phenomenonTimeString = date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        this.result_String = result;
    }
    public Observation(String phenomenonTimeString, double result) throws ParseException{
    	
    	String[] words=phenomenonTimeString.split("\\+");
    	String test = words[0].toString();
    	
    	DateTimeFormatter style1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    	LocalDateTime date = LocalDateTime.parse(test,style1);
        this.phenomenonTime = date.toEpochSecond(ZoneOffset.UTC);
        this.phenomenonTimeString = date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        this.result_double = result;
    }
    public Observation(String phenomenonTimeString, int result) throws ParseException{
    	LocalDateTime date = LocalDateTime.parse(phenomenonTimeString,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        this.phenomenonTime = date.toEpochSecond(ZoneOffset.UTC);
        this.phenomenonTimeString = date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        this.result_int = result;
    }
//    public Observation(JSONObject observationObj){
//        parseJSONObject(observationObj);
//    }
    
//    private void parseJSONObject(JSONObject observationObj){
//        if(observationObj.has("phenomenonTime")){
//            TimeInstance timeInstance = new TimeInstance( observationObj.getString("phenomenonTime") );
//            this.phenomenonTime = timeInstance.getCalendar().getTimeInMillis();
//            this.phenomenonTimeString = timeInstance.getTimeStringByDateFormatStr("yyyy-MM-dd'T'HH:mm:ssZ", TimeZone.getTimeZone("GMT+8"), "+08");
//        }
//        if(observationObj.has("result")){
//            try{
//                double result_tmp = observationObj.getDouble("result");
//                if(result_tmp>=Properties.THRESHOLD_PM){ // wrong reading -> set it as NAN number -999
//                    this.result = Properties.NAN;
//                }
//                else{
//                    this.result = Double.toString(result_tmp);
//                }
//            }
//            catch(Exception e){
//                this.result = observationObj.getString("result");
////                System.out.println("result is a String");
//            }
//        }
//    }
    
    public Observation(String sampleDate, String sampleDepth, String itemValue) {
    	LocalDate date = LocalDate.parse(sampleDate);
    	LocalDateTime datetime = LocalDateTime.parse(date.atTime(0, 0, 0, 0).toString(),DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.phenomenonTime = datetime.toEpochSecond(ZoneOffset.UTC);
        this.phenomenonTimeString = datetime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.result_String = itemValue;
	}
	public JSONObject getJSONObject(){
        JSONObject observationObj = new JSONObject();

        observationObj.put("phenomenonTime", this.phenomenonTimeString);
        if(this.result_String!=null)
            observationObj.put("result", this.result_String);
        else if(this.result_double!=Double.MAX_VALUE)
            observationObj.put("result", this.result_double);
        else if(this.result_int!=Integer.MAX_VALUE)
            observationObj.put("result", this.result_int);

        observationObj.put("parameters", this.parameters);

        return observationObj;
    }
    
    public String getJSONString(){
        JSONObject jsonObj = getJSONObject();
        return jsonObj.toString();
    }
    
    public void addToParameters(String key, Object value) {
    	this.parameters.put(key, value);
    }
    
    public static void main(String[] args) throws Exception {
        String phenomenonTimeString = "2017-07-12T02:55:51.417Z";
        double result = 25.80;
        
        Observation observation = new Observation(phenomenonTimeString, result);
        System.out.println(observation.getJSONString());
        
//        System.out.println(observation.phenomenonTimeString);
//        System.out.println(observation.phenomenonTime);
//        System.out.println(observation.result);
        
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(observation.phenomenonTime);
//        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//        String dateText = df2.format(cal.getTime());
//        System.out.println(dateText);
        
    }
}
