/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.datamodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import weatherstation.util.TimeInstance;
import org.json.JSONObject;

/**
 *
 * @author alec
 */
public class Observation {
    public String phenomenonTimeString = null;
    public long phenomenonTime = Long.MAX_VALUE;
    public String result_String = null;
    public double result_double = Double.MAX_VALUE;
    public int result_int = Integer.MAX_VALUE;
    
    public Observation(String phenomenonTimeString, String result) throws ParseException{
        
        TimeInstance timeInstance = new TimeInstance(phenomenonTimeString);
        this.phenomenonTime = timeInstance.getCalendar().getTimeInMillis();
        this.phenomenonTimeString = timeInstance.getTimeStringByDateFormatStr("yyyy-MM-dd'T'HH:mm:ssZ", TimeZone.getTimeZone("GMT+8"), "+08");
        
        this.result_String = result;
    }
    public Observation(String phenomenonTimeString, double result) throws ParseException{
        
        TimeInstance timeInstance = new TimeInstance(phenomenonTimeString);
        this.phenomenonTime = timeInstance.getCalendar().getTimeInMillis();
        this.phenomenonTimeString = timeInstance.getTimeStringByDateFormatStr("yyyy-MM-dd'T'HH:mm:ssZ", TimeZone.getTimeZone("GMT+8"), "+08");
        
        this.result_double = result;
    }
    public Observation(String phenomenonTimeString, int result) throws ParseException{
        
        TimeInstance timeInstance = new TimeInstance(phenomenonTimeString);
        this.phenomenonTime = timeInstance.getCalendar().getTimeInMillis();
        this.phenomenonTimeString = timeInstance.getTimeStringByDateFormatStr("yyyy-MM-dd'T'HH:mm:ssZ", TimeZone.getTimeZone("GMT+8"), "+08");
        
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
    
    public JSONObject getJSONObject(){
        JSONObject observationObj = new JSONObject();

        observationObj.put("phenomenonTime", this.phenomenonTimeString);
        if(this.result_String!=null)
            observationObj.put("result", this.result_String);
        else if(this.result_double!=Double.MAX_VALUE)
            observationObj.put("result", this.result_double);
        else if(this.result_int!=Integer.MAX_VALUE)
            observationObj.put("result", this.result_int);

        return observationObj;
    }
    
    public String getJSONString(){
        JSONObject jsonObj = getJSONObject();
        return jsonObj.toString();
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
