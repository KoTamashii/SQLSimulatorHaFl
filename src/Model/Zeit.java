package Model;

public class Zeit{

    private boolean dayOver;

    public Zeit(){
        System.out.println("Zeit Läuft");
        new java.util.Timer().schedule(
            new java.util.TimerTask() {
            @Override
            public void run() {
                dayOver = true;
            }
        }, 10*1000);

    }


    public boolean isDayOver() {
        return dayOver;
    }
}
