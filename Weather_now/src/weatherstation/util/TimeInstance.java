/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package weatherstation.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
/**
 *
 * @author David Chang
 */
public class TimeInstance{

    private boolean ifToLocalTimeZone=true;
    private String ogcTimesSring=null;
    private Calendar calendar=null;
    private TimeZone tz=null;
    private String timeZoneString=null;
    private String timeZoneId=null;    

    private boolean ifSetDefaultTimeZone=true;
    //private boolean ifShowTimeZone=false;    
    
    //private final String ogcDateFormatString="yyyy-MM-dd'T'HH:mm:ssZ";
    private final String ogcDateFormatString="yyyy-MM-dd'T'HH:mm:ss";
    //private final String ogcDateFormatString="MMMM d, yyyy h:mm a";
    private SimpleDateFormat dateFormat=new SimpleDateFormat(ogcDateFormatString);
    ///////////////////////////////////////////////////
    //Patch for NOAA instances
    private final String ogcDateFormatString1="yyyy-MM-dd'T'HH:mm:ss";
    private SimpleDateFormat dateFormat1=new SimpleDateFormat(ogcDateFormatString1);    
    ///////////////////////////////////////////////////
    
    private String dateFormatStr_original = null; //20111012 Alec192
    private String timeZoneStr_original = null; //20111012 Alec192
    private TimeZone tz_original = null; //20111012 Alec192

    private final String simpleDateFormatString="yyyy-MM-dd-HH-mm";
    //private final String jsDateFormatString    ="yyyy/MM/dd HH:mm:ss Z";
    private final String jsDateFormatString    ="yyyy/MM/dd HH:mm:ss";
    
    private final String[] dateFormatStringArray = {"yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                                                    "yyyy-MM-dd'T'HH:mm:ss.SSSz",
                                                    "yyyy-MM-dd'T'HH:mm:ss.SSS",
                                                    "yyyy-MM-dd'T'HH:mm:ssZ",
                                                    "yyyy-MM-dd'T'HH:mm:ssz",
                                                    "yyyy-MM-dd'T'HH:mm:ss",
                                                    "yyyy-MM-dd'T'HH:mmZ",
                                                    "yyyy-MM-dd'T'HH:mmz",
                                                    "yyyy-MM-dd'T'HH:mm",
                                                    "yyyy/MM/dd'T'HH:mm:ssZ",
                                                    "yyyy/MM/dd'T'HH:mm:ssz",
                                                    "yyyy/MM/dd'T'HH:mm:ss",
                                                    "yyyy.MM.dd G 'at' HH:mm:ss z",
                                                    "dd MMM yyyy HH:mm:ss Z z",
                                                    "yyyyy.MMMMM.dd GGG hh:mm aaa",
                                                    "EEE, dd MMM yyyy HH:mm:ss Z z",
                                                    "EEE, dd MMM yyyy HH:mm:ss z",
                                                    "EEE, dd MMM yyyy HH:mm:ss Z",
                                                    "EEE, d MMM yyyy HH:mm:ss z",
                                                    "EEE, d MMM yyyy HH:mm:ss Z",
                                                    "EEE, d MMM yyyy HH:mm:ss",
                                                    "MMMM dd, yyyy h:mm a Z",
                                                    "MMMM d, yyyy hh:mm a Z",
                                                    "MMMM dd, yyyy hh:mm a Z",
                                                    "MMMM d, yyyy h:mm a Z",
                                                    "MMMM dd, yyyy h:mm a z",
                                                    "MMMM d, yyyy hh:mm a z",
                                                    "MMMM dd, yyyy hh:mm a z",
                                                    "MMMM d, yyyy h:mm a z",
                                                    "MMMM dd, yyyy h:mm a",
                                                    "MMMM d, yyyy hh:mm a",
                                                    "MMMM dd, yyyy hh:mm a",
                                                    "MMMM d, yyyy h:mm a",
                                                    "MMMM dd, yyyy hmm a Z",
                                                    "MMMM d, yyyy hhmm a Z",
                                                    "MMMM dd, yyyy hhmm a Z",
                                                    "MMMM d, yyyy hmm a Z",
                                                    "MMMM dd, yyyy hmm a z",
                                                    "MMMM d, yyyy hhmm a z",
                                                    "MMMM dd, yyyy hhmm a z",
                                                    "MMMM d, yyyy hmm a z",
                                                    "MMMM dd, yyyy hmm a",
                                                    "MMMM d, yyyy hhmm a",
                                                    "MMMM dd, yyyy hhmm a",
                                                    "MMMM d, yyyy hmm a",

                                                    "MMMM dd, yyyy HH:mm Z",
                                                    "MMMM d, yyyy HH:mm Z",
                                                    "MMMM dd, yyyy H:mm Z",
                                                    "MMMM d, yyyy H:mm Z",
                                                    
                                                    "MMMM dd, yyyy HH:mm z",
                                                    "MMMM d, yyyy HH:mm z",
                                                    "MMMM dd, yyyy H:mm z",
                                                    "MMMM d, yyyy H:mm z",
                                                    
                                                    "MMMM dd, yyyy HH:mm",
                                                    "MMMM d, yyyy HH:mm",
                                                    "MMMM dd, yyyy H:mm",
                                                    "MMMM d, yyyy H:mm",
                                                    
                                                    "MMMM dd, yyyy HHmm Z",
                                                    "MMMM d, yyyy HHmm Z",
                                                    "MMMM dd, yyyy Hmm Z",
                                                    "MMMM d, yyyy Hmm Z",

                                                    "MMMM dd, yyyy HHmm z",
                                                    "MMMM d, yyyy HHmm z",                                                    
                                                    "MMMM dd, yyyy Hmm z",
                                                    "MMMM d, yyyy Hmm z",
                                                    
                                                    "MMMM dd, yyyy HHmm",
                                                    "MMMM d, yyyy HHmm",
                                                    "MMMM dd, yyyy Hmm",
                                                    "MMMM d, yyyy Hmm",

                                                    "dd-MMM-yyyy HH:mm:ss",
                                                    "yyyy-MM-dd HH:mm:ss.S",
                                                    "yyyy-MM-dd'TS'HH:mm:ss.SSS",
                                                    "yyyy-MM-dd",
                                                    "yyyyMMddHHmmssZ",
                                                    "yyyyMMddHHmmssz",
                                                    "yyyyMMddHHmmss",                                                    
                                                    "yyyy/MM/dd HH:mm:ss Z",
                                                    "yyyy/MM/dd HH:mm:ss z",
                                                    "yyyy/MM/dd HH:mm:ssZ",
                                                    "yyyy/MM/dd HH:mm:ssz",
                                                    "yyyy/MM/dd HH:mm:ss",                                                    
                                                    };
    
    public TimeInstance(String timeString){
        if(ifSetDefaultTimeZone)
            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        this.idetifyTimeformat(timeString);

        if(ifToLocalTimeZone){
            try{
                TimeZone tzTmp=TimeZone.getDefault();
                this.calendar=toTimeZone(this.calendar, tzTmp);
                this.setTimeZone(tzTmp);
                this.setOGCTimesSring();
            }catch(Exception e){}
        }
    }

    public TimeInstance(String timeString, TimeZone tz){
        if(ifSetDefaultTimeZone)
            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        this.setTimeZone(tz);
        dateFormat.setTimeZone(tz);
        this.idetifyTimeformat(timeString,tz);
        //System.out.println(tz.getID());
        //System.out.println(tz.getRawOffset()/3600000);
        //System.out.println(this.ogcTimesSring);


        if(ifToLocalTimeZone){
            TimeZone tzTmp=TimeZone.getDefault();
            //System.out.println(tzTmp.getID());
            //System.out.println(tzTmp.getRawOffset()/3600000);
            this.calendar=toTimeZone(this.calendar, tzTmp);
            this.setTimeZone(tzTmp);
            this.setOGCTimesSring();
            //System.out.println(this.ogcTimesSring);
        }
    }
    
    public TimeInstance(Calendar calendar){
        if(ifSetDefaultTimeZone)
            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        this.calendar=calendar;
        tz=this.calendar.getTimeZone();
        this.setTimeZone(tz);
        dateFormat.setTimeZone(this.tz);
        this.setOGCTimesSring();

        if(ifToLocalTimeZone){
            TimeZone tzTmp=TimeZone.getDefault();
            this.calendar=toTimeZone(this.calendar, tzTmp);
            this.setTimeZone(tzTmp);
            this.setOGCTimesSring();
        }
    }
    
    public void setTimeZone(TimeZone tz){
        this.tz=tz;
        this.timeZoneString=tz.getID();
        if(this.timeZoneString.startsWith("GMT"))
            this.timeZoneString=tz.getID().replace("GMT", "");
        else{
            double offset=tz.getRawOffset()/3600000.;
            int offestAbs=(int)(Math.abs(offset));
            StringBuffer tmp=new StringBuffer();
            if(offset>=0){
                tmp.append("+");
                if(offestAbs<10)    tmp.append("0");
                tmp.append(offestAbs);
                tmp.append(":00");
            }else{
                tmp.append("-");
                if(offestAbs<10)    tmp.append("0");
                tmp.append(offestAbs);
                tmp.append(":00");
            }
            this.timeZoneString=tmp.toString();
        }
    }

    private void setOGCTimesSring(){
        if(this.tz!=null)
            this.dateFormat.setTimeZone(this.tz);
//        if(SettingsForPatch.ifShowTimeZone){
            this.ogcTimesSring=dateFormat.format(this.calendar.getTime());
            if(this.tz==null || this.timeZoneString.equals("")){ //20110217 Alec192
//                if(SettingsForPatch.GMTTimeZoneisNumber)
//                    this.ogcTimesSring+="+00:00";
//                else
                    this.ogcTimesSring+="Z";
            }
            else
                this.ogcTimesSring+=this.timeZoneString;
//        }
//        else{
//            ///////////////////////////////////////////////////
//            //Patch for NOAA instances
//            this.ogcTimesSring=dateFormat1.format(this.calendar.getTime());
//            this.ogcTimesSring+="Z";
//            ///////////////////////////////////////////////////
//        }
    }
    
    public String getOriginalDateFormat(){
        return dateFormatStr_original;
    }
    public String getOriginalTimeZoneStr(){
        return timeZoneStr_original;
    }
    public TimeZone getOriginalTimeZone(){
        return tz_original;
    }
    public String getTimeStringByDateFormatStr(String dateFormatStr, TimeZone tz_original, String timeZoneStr){
        if(dateFormatStr==null){
            dateFormatStr = ogcDateFormatString;
            timeZoneStr = "Z";
        }
        else if(dateFormatStr.endsWith("Z")){
            dateFormatStr = dateFormatStr.substring(0, dateFormatStr.length()-1);
        }
        
        Calendar calendar_tmp = null;
        Date date = null;
        if(tz_original!=null){
            Calendar mbCal = new GregorianCalendar(tz_original);
            mbCal.setTimeInMillis(this.calendar.getTime().getTime());

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
            cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
            cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
            cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
            date = cal.getTime();
        }
        else{
            calendar_tmp=Calendar.getInstance();
            calendar_tmp.setTimeInMillis(this.calendar.getTimeInMillis());
            date = calendar_tmp.getTime();
        }
        String timeString_original = new SimpleDateFormat(dateFormatStr).format(date);
        if(timeZoneStr!=null){
            timeString_original = timeString_original + timeZoneStr;
        }
        return timeString_original;
    }
    
    private void idetifyTimeformat(String timeString){
//        if(SettingsForPatch.timeZoneModify){
//        //if(true){
//            //System.out.println(timeString);
//            try{
//                int tmpIndx=timeString.lastIndexOf(":");
//                timeString=timeString.substring(0, tmpIndx)+timeString.substring(tmpIndx+1);
//            }catch(Exception e){}
//            //System.out.println(timeString);
//        }

        SimpleDateFormat inputDateFormat;
        Date timeStamp;
        for(String dateFormatString:dateFormatStringArray)
        {
            inputDateFormat = new SimpleDateFormat(dateFormatString);
            try{
                timeStamp = inputDateFormat.parse(timeString);
                if(dateFormatStr_original==null){ //20111012_1531 Alec192
                    dateFormatStr_original = dateFormatString;
                }
                if(dateFormatString.equals("yyyy-MM-dd'T'HH:mm")){
                    if(timeString.length()==16){
                        this.setTimeZone(TimeZone.getTimeZone("GMT"));
                        timeZoneStr_original = "";
                    }
                    else if(timeString.length()==17&&(timeString.endsWith("Z")||
                            timeString.endsWith("z"))){
                        this.setTimeZone(TimeZone.getTimeZone("GMT"));
                        timeZoneStr_original = "Z";
                    }
                    else if(timeString.length()>17){// if(timeString.length()==22){
                        String tmp=timeString.substring(16,19);
                        timeZoneStr_original = timeString.substring(16,timeString.length());
                        if(tmp.startsWith("+")) tmp=tmp.substring(1);
                        int diff=Integer.parseInt(tmp);
                        //System.out.println(diff);
                        String tmpTimeZone="GMT";
                        if(diff>0)  tmpTimeZone+=("+"+diff);
                        else        tmpTimeZone+=(diff);
                        //System.out.println(tmpTimeZone);
                        this.setTimeZone(TimeZone.getTimeZone(tmpTimeZone));
                    }
                    //calendar=toGMT(this.calendar);
                }
                else if(dateFormatString.equals("yyyy-MM-dd'T'HH:mm:ss")){
                    if(timeString.length()==19){
                        this.setTimeZone(TimeZone.getTimeZone("GMT"));
                        timeZoneStr_original = "";
                     }else if(timeString.length()==20&&(timeString.endsWith("Z")||
                            timeString.endsWith("z"))){
                        this.setTimeZone(TimeZone.getTimeZone("GMT"));
                        timeZoneStr_original = "Z";
                    }else if(timeString.length()>20){// if(timeString.length()==24){
                        String tmp=timeString.substring(19,22);
                        timeZoneStr_original = timeString.substring(19,timeString.length());
                        if(tmp.startsWith("+")) tmp=tmp.substring(1);
                        int diff=Integer.parseInt(tmp);
                        //System.out.println(diff);
                        String tmpTimeZone="GMT";
                        if(diff>0)  tmpTimeZone+=("+"+diff);
                        else        tmpTimeZone+=(diff);
                        //System.out.println(tmpTimeZone);
                        this.setTimeZone(TimeZone.getTimeZone(tmpTimeZone));
                    }
                    //calendar=toGMT(this.calendar);                    
                }else if(timeString.length()==25 &&
                        dateFormatString.equals("yyyy/MM/dd HH:mm:ss Z")){
                    String tmpString=timeString.substring(20,25);
                    timeZoneStr_original = timeString.substring(19,25);
                    tmpString=tmpString.substring(0,3);
                    //System.out.println(tmpString);
                    if(tmpString.startsWith("+")) tmpString=tmpString.substring(1);
                    int diff=Integer.parseInt(tmpString);
                    //System.out.println(diff);
                    String tmpTimeZone="GMT";
                    if(diff>0)  tmpTimeZone+=("+"+diff);
                    else        tmpTimeZone+=(diff);

                    //StringBuffer sb=new StringBuffer().append(tmpString.substring(0,3));
                    //String tmpTimeZone="GMT"+Integer.parseInt(sb.toString());

                    //System.out.println(tmpTimeZone);
                    this.setTimeZone(TimeZone.getTimeZone(tmpTimeZone));
                    //calendar=toGMT(this.calendar);
                }else if(timeString.length()==26 &&
                        dateFormatString.equals("yyyy/MM/dd HH:mm:ss")){
                    String tmpString=timeString.substring(20,26);
                    timeZoneStr_original = tmpString;
                    tmpString=tmpString.substring(0,3);
                    //System.out.println(tmpString);
                    if(tmpString.startsWith("+")) tmpString=tmpString.substring(1);
                    int diff=Integer.parseInt(tmpString);
                    //System.out.println(diff);
                    String tmpTimeZone="GMT";
                    if(diff>0)  tmpTimeZone+=("+"+diff);
                    else        tmpTimeZone+=(diff);

                    //System.out.println(tmpString);
                    //StringBuffer sb=new StringBuffer().append(tmpString.substring(0,3));
                    //String tmpTimeZone="GMT"+Integer.parseInt(sb.toString());
                    //System.out.println(tmpTimeZone);
                    this.setTimeZone(TimeZone.getTimeZone(tmpTimeZone));
                    //calendar=toGMT(this.calendar);
                }
                else if(/*timeString.length()==24 &&*/
                        dateFormatString.equals("yyyy-MM-dd'T'HH:mm:ssZ")){
                    String tmpString=timeString.substring(19,24);
                    timeZoneStr_original = timeString.substring(19,timeString.length());
                    tmpString=tmpString.substring(0,3);
                    //System.out.println(tmpString);
                    if(tmpString.startsWith("+")) tmpString=tmpString.substring(1);
                    int diff=Integer.parseInt(tmpString);
                    //System.out.println(diff);
                    String tmpTimeZone="GMT";
                    if(diff>0)  tmpTimeZone+=("+"+diff);
                    else        tmpTimeZone+=(diff);

                    //System.out.println(tmpString);
                    //StringBuffer sb=new StringBuffer().append(tmpString.substring(0,3));
                    //String tmpTimeZone="GMT"+Integer.parseInt(sb.toString());
                    //System.out.println(tmpTimeZone);
                    this.setTimeZone(TimeZone.getTimeZone(tmpTimeZone));
                    //calendar=toGMT(this.calendar);
                }
                else if(dateFormatString.equals("yyyy-MM-dd'T'HH:mmZ")){
                    String tmpString=timeString.substring(16,19);
                    timeZoneStr_original = timeString.substring(16,timeString.length());
                    //System.out.println(tmpString);
                    if(tmpString.startsWith("+")) tmpString=tmpString.substring(1);
                    int diff=Integer.parseInt(tmpString);
                    //System.out.println(diff);
                    String tmpTimeZone="GMT";
                    if(diff>0)  tmpTimeZone+=("+"+diff);
                    else        tmpTimeZone+=(diff);

                    //System.out.println(tmpString);
                    //StringBuffer sb=new StringBuffer().append(tmpString.substring(0,3));
                    //String tmpTimeZone="GMT"+Integer.parseInt(sb.toString());
                    //System.out.println(tmpTimeZone);
                    this.setTimeZone(TimeZone.getTimeZone(tmpTimeZone));
                    //calendar=toGMT(this.calendar);
                }else if(dateFormatString.equals("yyyy-MM-dd'T'HH:mm:ss.SSS")){
                    if(timeString.length()==24&&(timeString.endsWith("Z")||
                            timeString.endsWith("z"))){
                        this.setTimeZone(TimeZone.getTimeZone("GMT"));
                        timeZoneStr_original = "Z";
                    }else if(timeString.length()==23){
                        this.setTimeZone(TimeZone.getTimeZone("GMT"));
                        timeZoneStr_original = "";
                    }else if(timeString.length()>=24){
                        String tmp=timeString.substring(23,26);
                        timeZoneStr_original = timeString.substring(23,timeString.length());
                        if(tmp.startsWith("+")) tmp=tmp.substring(1);
                        int diff=Integer.parseInt(tmp);
                        //System.out.println(diff);
                        String tmpTimeZone="GMT";
                        if(diff>0)  tmpTimeZone+=("+"+diff);
                        else        tmpTimeZone+=(diff);
                        //System.out.println(tmpTimeZone);
                        this.setTimeZone(TimeZone.getTimeZone(tmpTimeZone));
                    }
                    //calendar=toGMT(this.calendar);                    
                }
                
                if(this.tz!=null){
                    //System.out.println("dateFormatString="+dateFormatString);
                    inputDateFormat.setTimeZone(this.tz);
                    timeStamp = inputDateFormat.parse(timeString);
                    calendar=Calendar.getInstance(tz);
                    //System.out.println(this.timeZoneString);
                }
                else
                    calendar=Calendar.getInstance();
                
                tz_original = this.tz;
                calendar.setTime(timeStamp);
                this.setOGCTimesSring();
                //System.out.println("dateFormatString="+dateFormatString);
                return;
            }
            catch(Exception e){
            }
        }
    }

    private void idetifyTimeformat(String timeString,TimeZone tz){
        SimpleDateFormat inputDateFormat;
        Date timeStamp;
        for(String dateFormatString:dateFormatStringArray){
            inputDateFormat = new SimpleDateFormat(dateFormatString);
            inputDateFormat.setTimeZone(tz);
            try{
                timeStamp = inputDateFormat.parse(timeString);
                calendar=Calendar.getInstance(tz);
                calendar.setTime(timeStamp);
                //calendar=toGMT(this.calendar);
                this.setOGCTimesSring();
                //System.out.println("dateFormatString="+dateFormatString);
                return;
            }
            catch(Exception e){
            }
        }
    }

    // Access parameters in the class
    public Calendar getCalendar(){
        return this.calendar;
    }
    
    public String getTimeString(){
        return this.ogcTimesSring;
    }    
    
    public String getSimpleTimeString(){  
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(this.simpleDateFormatString);
        if(this.tz!=null)   {
            simpleDateFormat.setTimeZone(this.tz);
        }
        return simpleDateFormat.format(this.calendar.getTime());
    }    

    public String getJsTimeString(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(this.jsDateFormatString);
        if(this.tz!=null){
            simpleDateFormat.setTimeZone(this.tz);
            //System.out.println("Set time zone");
        }

        String tmpStr=simpleDateFormat.format(this.calendar.getTime());
        tmpStr+=" ";
        if(this.tz==null)   tmpStr+="Z";
        else    tmpStr+=this.timeZoneString;

        return tmpStr;
    }

    public String getJsTimeStringWithoutTimeZone(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(this.jsDateFormatString);
        if(this.tz!=null){
            simpleDateFormat.setTimeZone(this.tz);
            //System.out.println("Set time zone");
        }
        String tmpStr=simpleDateFormat.format(this.calendar.getTime());
        return tmpStr;
    }

    // parse a input document element
//    public boolean parseDocElement(Element timeInstantNode){        
//        Element timePositionNode=ParseXml.findChildByLocalNameFromNextLevel(timeInstantNode, "timePosition");
//        String timeString=null;
//        if(timePositionNode==null)
//            return true; //return false; //20101125 Alec192
//        else{
//            timeString= timePositionNode.getText();
//            if(timeString==null)
//                return true; //return false; //20101125 Alec192
//        }
//        this.idetifyTimeformat(timeString);
//
//        return true;
//     }        

    public static Calendar toGMT(Calendar cal) {
        Calendar cal1=toTimeZone(cal,"GMT");
        return cal1;
    }

    public static Calendar toTimeZone(Calendar cal,String tzString) {

        TimeZone tz=TimeZone.getTimeZone(tzString);
        return toTimeZone(cal,tz);
    }

    private static Calendar toTimeZone(Calendar cal,TimeZone tz){
        //Calendar cal1 = (Calendar)cal.clone();
        Calendar cal1 = Calendar.getInstance(tz);
        TimeZone tzSave = cal.getTimeZone();
        
        cal.setTimeZone(tz);

        cal1.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)
        , cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY)
        , cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));

        cal1.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));

        cal.setTimeZone(tzSave);

        cal.getTime();
        return cal1;
    }



    // Output a document element
//    public Element toDocElement(){
//       
//        Element timeInstantNode     = new Element("TimeInstant",XmlSweNamespace.getGmlNamespace());
//        Element timePositionNode= new Element("timePosition",XmlSweNamespace.getGmlNamespace());
//        String timePositionString=this.getTimeString();
//        timePositionNode.addContent(timePositionString);
//        timeInstantNode.addContent(timePositionNode);
//
//        return timeInstantNode;
//    }    


    public void test1(){
        //TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2001, 7, 1, 0, 0, 0);
        System.out.println("/////////////////////////////////////");
        System.out.println(cal1.getTime());
        System.out.println(cal1.get(Calendar.MONTH));
        System.out.println(cal1.get(Calendar.DATE));
        System.out.println(cal1.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal1.getTimeZone().getID());

        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(cal1.getTime());
        System.out.println(cal1.getTimeZone().getID());
        System.out.println(cal1.get(Calendar.MONTH));
        System.out.println(cal1.get(Calendar.DATE));
        System.out.println(cal1.get(Calendar.HOUR_OF_DAY));
        System.out.println("/////////////////////////////////////");

    }

    public void test2(){
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2001, 7, 1, 0, 0, 0);
        System.out.println("/////////////////////////////////////");
        System.out.println(cal1.getTime());
        System.out.println(cal1.get(Calendar.MONTH));
        System.out.println(cal1.get(Calendar.DATE));
        System.out.println(cal1.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal1.getTimeZone().getID());

        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        //int save = cal1.get(Calendar.MILLISECOND);
        cal1.set(Calendar.MILLISECOND, 0);
        System.out.println(cal1.getTime());        
        System.out.println(cal1.get(Calendar.MONTH));
        System.out.println(cal1.get(Calendar.DATE));
        System.out.println(cal1.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal1.getTimeZone().getID());
        System.out.println("/////////////////////////////////////");
    }

    public void test3(){
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2001, 7, 1, 0, 0, 0);
        System.out.println("/////////////////////////////////////");
        System.out.println(cal1.getTime());
        System.out.println(cal1.get(Calendar.MONTH));
        System.out.println(cal1.get(Calendar.DATE));
        System.out.println(cal1.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal1.getTimeZone().getID());

        //Calendar cal2=toGMT(cal1);
        Calendar cal2=toTimeZone(cal1,"GMT-5");
        System.out.println(cal2.getTime());
        System.out.println(cal2.get(Calendar.MONTH));
        System.out.println(cal2.get(Calendar.DATE));
        System.out.println(cal2.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal2.getTimeZone().getID());
        System.out.println("/////////////////////////////////////");
    }
    
    // Testing function
    public static void main(String[] args) {
        //Test 1
        String timeString="02001.July.04 AD 12:08 PM";
//        TimeInstance t0 = new TimeInstance(timeString);
//        System.out.println("Input Time String = "+timeString);
//        System.out.println("Original Time Str = "+t0.getTimeStringByDateFormatStr(t0.getOriginalDateFormat(), t0.getOriginalTimeZone(), t0.getOriginalTimeZoneStr()));
//        System.out.println("Assigned Time Str = "+t0.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT-5"), "-05"));
//        System.out.println("OGC Time String   = "+t0.getTimeString());
//        System.out.println("TimeZone = "+t0.getCalendar().getTimeZone().getDisplayName());
//        System.out.println("TimeZone = "+t0.getCalendar().getTimeZone().getID());
//        System.out.println("TimeZone = "+t0.getCalendar().getTimeZone().getRawOffset()/3600000.);

        //Test 2
        timeString="2009-05-17T12:56-06:00";
        TimeInstance t1 = new TimeInstance(timeString);
        System.out.println("Input Time String = "+timeString);
        System.out.println("Original Time Str = "+t1.getTimeStringByDateFormatStr(t1.getOriginalDateFormat(), t1.getOriginalTimeZone(), t1.getOriginalTimeZoneStr()));
        System.out.println("Assigned Time Str = "+t1.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT-5"), "-05"));
        System.out.println("OGC Time String   = "+t1.getTimeString());
        System.out.println("TimeZone = "+t1.getCalendar().getTimeZone().getDisplayName());
        System.out.println("TimeZone = "+t1.getCalendar().getTimeZone().getID());
        System.out.println("TimeZone = "+t1.getCalendar().getTimeZone().getRawOffset()/3600000.);

//        //Test 3
//        timeString="2009-05-17T12:56-0600";
//        TimeInstance t11 = new TimeInstance(timeString);
//        System.out.println("Input Time String = "+timeString);
//        System.out.println("Original Time Str = "+t11.getTimeStringByDateFormatStr(t11.getOriginalDateFormat(), t11.getOriginalTimeZone(), t11.getOriginalTimeZoneStr()));
//        System.out.println("Assigned Time Str = "+t11.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT-5"), "-05"));
//        System.out.println("OGC Time String   = "+t11.getTimeString());
//        System.out.println("TimeZone = "+t11.getCalendar().getTimeZone().getDisplayName());
//        System.out.println("TimeZone = "+t11.getCalendar().getTimeZone().getID());
//        System.out.println("TimeZone = "+t11.getCalendar().getTimeZone().getRawOffset()/3600000.);
//
//        //Test 3
//        timeString="2009-05-17T12:56-06";
//        TimeInstance t33 = new TimeInstance(timeString);
//        System.out.println("Input Time String = "+timeString);
//        System.out.println("Original Time Str = "+t33.getTimeStringByDateFormatStr(t33.getOriginalDateFormat(), t33.getOriginalTimeZone(), t33.getOriginalTimeZoneStr()));
//        System.out.println("Assigned Time Str = "+t33.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT-5"), "-05"));
//        System.out.println("OGC Time String   = "+t33.getTimeString());
//        System.out.println("TimeZone = "+t33.getCalendar().getTimeZone().getDisplayName());
//        System.out.println("TimeZone = "+t33.getCalendar().getTimeZone().getID());
//        System.out.println("TimeZone = "+t33.getCalendar().getTimeZone().getRawOffset()/3600000.);
//        
//        //Test 4
////        timeString="2008-07-16T12:15Z";
////        TimeInstance t2 = new TimeInstance(timeString);
////        System.out.println("Input Time String = "+timeString);
////        System.out.println("Original Time Str = "+t2.getTimeStringByDateFormatStr(t2.getOriginalDateFormat(), t2.getOriginalTimeZone(), t2.getOriginalTimeZoneStr()));
////        System.out.println("Assigned Time Str = "+t2.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT-5"), "-05"));
////        System.out.println("OGC Time String   = "+t2.getTimeString());
////        System.out.println("TimeZone = "+t2.getCalendar().getTimeZone().getDisplayName());
////        System.out.println("TimeZone = "+t2.getCalendar().getTimeZone().getID());
////        System.out.println("TimeZone = "+t2.getCalendar().getTimeZone().getRawOffset()/3600000.);
////        System.out.println(new ElementToXmlString(t2.toDocElement()).getXmlString());
////        System.out.println(new ElementToXmlString(new TimeInstance(t2.toDocElement()).toDocElement()).getXmlString());
//        
//        //Test 5
//        timeString="2008-07-16T12:15:36+02:00";
//        TimeInstance t3 = new TimeInstance(timeString);
//        System.out.println("Input Time String = "+timeString);
//        System.out.println("Original Time Str = "+t3.getTimeStringByDateFormatStr(t3.getOriginalDateFormat(), t3.getOriginalTimeZone(), t3.getOriginalTimeZoneStr()));
//        System.out.println("Assigned Time Str = "+t3.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT-5"), "-05"));
//        System.out.println("OGC Time String   = "+t3.getTimeString());
//        System.out.println("TimeZone = "+t3.getCalendar().getTimeZone().getDisplayName());
//        System.out.println("TimeZone = "+t3.getCalendar().getTimeZone().getID());
//        System.out.println("TimeZone = "+t3.getCalendar().getTimeZone().getRawOffset()/3600000.);
//        
//        //Test 6
//        timeString="2008-07-16T12:15:36+0200";
//        TimeInstance t6 = new TimeInstance(timeString);
//        System.out.println("Input Time String = "+timeString);
//        System.out.println("Original Time Str = "+t6.getTimeStringByDateFormatStr(t6.getOriginalDateFormat(), t6.getOriginalTimeZone(), t6.getOriginalTimeZoneStr()));
//        System.out.println("Assigned Time Str = "+t6.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT-5"), "-05"));
//        System.out.println("OGC Time String   = "+t6.getTimeString());
//        System.out.println("TimeZone = "+t6.getCalendar().getTimeZone().getDisplayName());
//        System.out.println("TimeZone = "+t6.getCalendar().getTimeZone().getID());
//        System.out.println("TimeZone = "+t6.getCalendar().getTimeZone().getRawOffset()/3600000.);
//        
//        //Test 7
//        timeString="2008-07-16T12:15:36+02";
//        TimeInstance t7 = new TimeInstance(timeString);
//        System.out.println("Input Time String = "+timeString);
//        System.out.println("Original Time Str = "+t7.getTimeStringByDateFormatStr(t7.getOriginalDateFormat(), t7.getOriginalTimeZone(), t7.getOriginalTimeZoneStr()));
//        System.out.println("Assigned Time Str = "+t7.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT-5"), "-05"));
//        System.out.println("OGC Time String   = "+t7.getTimeString());
//        System.out.println("TimeZone = "+t7.getCalendar().getTimeZone().getDisplayName());
//        System.out.println("TimeZone = "+t7.getCalendar().getTimeZone().getID());
//        System.out.println("TimeZone = "+t7.getCalendar().getTimeZone().getRawOffset()/3600000.);
        
//        //Test 8
//        timeString="2008-07-16T12:15:36Z";
//        TimeInstance t8 = new TimeInstance(timeString);
//        System.out.println("Input Time String = "+timeString);
//        System.out.println("Original Time Str = "+t8.getTimeStringByDateFormatStr(t8.getOriginalDateFormat(), t8.getOriginalTimeZone(), t8.getOriginalTimeZoneStr()));
//        System.out.println("Assigned Time Str = "+t8.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT+8"), "+08"));
//        System.out.println("OGC Time String   = "+t8.getTimeString());
//        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getDisplayName());
//        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getID());
//        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getRawOffset()/3600000.);
        
        //Test 9
        timeString="2017-07-12T02:55:51.417Z";
        TimeInstance t9 = new TimeInstance(timeString);
        System.out.println("Input Time String = "+timeString);
        System.out.println("Original Time Str = "+t9.getTimeStringByDateFormatStr(t9.getOriginalDateFormat(), t9.getOriginalTimeZone(), t9.getOriginalTimeZoneStr()));
        System.out.println("Assigned Time Str = "+t9.getTimeStringByDateFormatStr("yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("GMT+8"), "+08"));
        System.out.println("OGC Time String   = "+t9.getTimeString());
        System.out.println("TimeZone = "+t9.getCalendar().getTimeZone().getDisplayName());
        System.out.println("TimeZone = "+t9.getCalendar().getTimeZone().getID());
        System.out.println("TimeZone = "+t9.getCalendar().getTimeZone().getRawOffset()/3600000.);

//        timeString="2008-04-01T17:49:00.000+02:00";
//        //timeString="2008-04-01T17:49:00.000";
//        System.out.println(timeString);
//        System.out.println(timeString.length());
//        t2 = new TimeInstance(timeString);
//        System.out.println(t2.getTimeString());
//        System.out.println(new TimeInstance(t2.getTimeString()).getTimeString());
        
        //String timeString="2009-05-05T17:57:00Z";
        //String timeString="1958-07-25 00:00:00.0";
        //String timeString="Sat, 28 Aug 2010 23:17:27";
        //String timeString="August 30, 2010 1:50 pm PDT";
        //String timeString="2009-05-17T12:56:00-0600";
/*
        //String timeString="2009-12-11T00:00:00-05:00";
        String timeString="2009-10-01T00:00-05:00";

        //String timeString="2009-12-10 00:00:00.0";
        System.out.println(timeString.lastIndexOf(":"));

        
        //timeString=timeString.substring(0, timeString.lastIndexOf(" "));
        
        //String timeString="2010-01-13T19:12:36+02:00";
        //String timeString="2010-01-12T13:12:40-0500";
        //String timeString="2010-01-12T13:12:40-05:00";
        //String timeString="2006/08/15 00:00:00 -04:00";
        //String timeString="2005-08-16T00:59:59-04:00";
        System.out.println(timeString.length());
        TimeInstance t8 = new TimeInstance(timeString);
        System.out.println("Input Time String = "+timeString);
        System.out.println("OGC Time String   = "+t8.getTimeString());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getDisplayName());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getID());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getRawOffset()/3600000.);
        System.out.println("JS Time String   = "+t8.getJsTimeString());
        System.out.println("JS Time String   = "+t8.getJsTimeStringWithoutTimeZone());
        System.out.println(new ElementToXmlString(t8.toDocElement()).getXmlString());
        System.out.println(new ElementToXmlString(new TimeInstance(t8.toDocElement()).toDocElement()).getXmlString());

        //timeString="2006/08/15 17:30:12 -04:00";
        //timeString="30-Sep-2006 23:00:00";
        //timeString="1958-07-25 00:00:00.0";
        //timeString="August 30, 2010 1:50 pm PDT";
        timeString="August 30, 2010 1300 UTC";

        System.out.println("Input Time String = "+timeString);

        //TimeZone tz=TimeZone.getTimeZone("GMT-4");
        TimeZone tz=TimeZone.getTimeZone("GMT");
        System.out.println(tz.getID());
        t8 = new TimeInstance(timeString,tz);
        //t2 = new TimeInstance(timeString);
        System.out.println("OGC Time String   = "+t8.getTimeString());
        System.out.println("Simple Time String   = "+t8.getSimpleTimeString());
        System.out.println("JS Time String   = "+t8.getJsTimeString());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getDisplayName());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getID());
        String tmpString=t8.getCalendar().getTimeZone().getID();
        //tmpString=tmpString.substring(3,6);
        //System.out.println(tmpString);

        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getRawOffset()/3600000.);
        System.out.println(new ElementToXmlString(t8.toDocElement()).getXmlString());
        System.out.println(new ElementToXmlString(new TimeInstance(t8.toDocElement()).toDocElement()).getXmlString());
        Calendar c2=t8.getCalendar();
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        t8 = new TimeInstance(c2);
        System.out.println("Input Time String = "+c2.toString());
        System.out.println("OGC Time String   = "+t8.getTimeString());
        System.out.println("Simple Time String   = "+t8.getSimpleTimeString());
        System.out.println("JS Time String   = "+t8.getJsTimeString());


        //TimeZone tmpTz=TimeZone.getTimeZone("PST");// No PDT allowed
        //TimeZone tmpTz=TimeZone.getTimeZone("EST");// No PDT allowed
        TimeZone tmpTz=TimeZone.getTimeZone("GMT-5");// No PDT allowed
        System.out.println(tmpTz.getDisplayName());
        System.out.println(tmpTz.getID());
        System.out.println(tmpTz.getRawOffset());


        timeString="1875-05-01TS00:00:00.000";
        tz=TimeZone.getTimeZone("GMT-5");
        t8 = new TimeInstance(timeString,tz);
        System.out.println(t8.getTimeString());
*/
        /*
        timeString="2009/05/17T12:56:00";
        t8 = new TimeInstance(timeString);
        System.out.println("Input Time String = "+timeString);
        System.out.println("OGC Time String   = "+t8.getTimeString());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getDisplayName());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getID());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getRawOffset()/3600000.);
        System.out.println(new ElementToXmlString(t8.toDocElement()).getXmlString());        
        System.out.println(new ElementToXmlString(new TimeInstance(t8.toDocElement()).toDocElement()).getXmlString());

        //Test 5
        timeString="20090219125600";
        //TimeZone tz=TimeZone.getTimeZone("GMT-0600");
        TimeZone tz=TimeZone.getTimeZone("GMT");
        t8 = new TimeInstance(timeString,tz);
        //t2.setTimeZone(tz);
        System.out.println("Input Time String = "+timeString);
        System.out.println("OGC Time String   = "+t8.getTimeString());
        System.out.println("TimeZone = "+tz.getDisplayName());
        System.out.println("TimeZone = "+tz.getID());
        System.out.println("TimeZone = "+tz.getRawOffset()/3600000.);
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getDisplayName());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getID());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getRawOffset()/3600000.);
        //Test 6
        timeString="Wed, 4 Jul 2001 12:08:56 -0200";
        t8 = new TimeInstance(timeString);
        System.out.println("Input Time String = "+timeString);
        System.out.println("OGC Time String   = "+t8.getTimeString());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getDisplayName());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getID());
        System.out.println("TimeZone = "+t8.getCalendar().getTimeZone().getRawOffset()/3600000.);

         
        t8.test1();
        t8.test2();
        t8.test3();
        */
        
//        String urlStr = "http://www.csiro.au/sensorweb/BOM_SOS/sos";
//        URL url = null;
//        try {
//            url = new URL(urlStr);
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(TimeInstance.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        SettingsForPatch.setPatchSettings(url);
//        
//        String timeString="2011-09-21T19:55:46Z";
//        TimeInstance t0 = new TimeInstance(timeString);
//        System.out.println("Input Time String = "+timeString);
//        System.out.println("OGC Time String   = "+t0.getTimeString());
//        
//        long endTime, startTime;
//        TimeInstance time = new TimeInstance(timeString);
//        Calendar tmp = time.getCalendar();
//        endTime = tmp.getTimeInMillis();
//        Calendar endCal = Calendar.getInstance();
//        endCal.setTimeInMillis(endTime+(long)60*(long)60*(long)1000);
//
//        startTime = endTime - (24+1)*(long)60*(long)60*(long)1000;
//        Calendar startCal = Calendar.getInstance();
//        startCal.setTimeInMillis(startTime);
//        
//        System.out.println(new TimeInstance(startCal).getTimeString()+" to "+new TimeInstance(endCal).getTimeString());
    }  
}
