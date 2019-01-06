package Model;

public class Zeit{

    private boolean dayOver;

    public Zeit(){
        dayOver = false;

        startDayTimer();

    }

    private void startDayTimer(){
        System.out.println("Zeit Läuft");
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        dayOver = !dayOver;
                        startDayTimer();
                    }
                }, 10*1000);
    }


    public boolean isDayOver() {
        return dayOver;
    }
}
