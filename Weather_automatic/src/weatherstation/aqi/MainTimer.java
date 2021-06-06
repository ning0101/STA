/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.aqi;

/**
 *
 * @author Sean
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainTimer {

    public static void main(String[] args) throws ParseException {

        MainTimer timerDemo = new MainTimer();
        timerDemo.testScheduleDateAndPeriod();
    }

    void testScheduleDateAndPeriod() throws ParseException {

        Timer timer = new Timer();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date firstTime = dateFormatter.parse("2015/06/30 21:27:00");

        // schedule(TimerTask task, Date firstTime, long period)
        timer.schedule(new AQIFeeder(), 1000, weatherstation.util.Properties.FEEDING_FREQUENCY);
//        timer.schedule(new AQIFeeder(), 1000);

//         try {
//             Thread.sleep(10000);
//         }
//             catch(InterruptedException e) {
//         }
// 
//         timer.cancel();
    }
}
