package Model;

import java.util.Timer;
import java.util.TimerTask;

public class Zeit{

    private boolean dayOver;

    public Zeit(){

        Timer timeClocker = new Timer();
        timeClocker.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                dayOver = true;
            }
        }, 10*1000, 10*1000);
    }

}
