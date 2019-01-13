package Model;


public class Zeit{

    private boolean dayOver;
    private int dayCounter;


    public Zeit(){
        dayOver = false;
        dayCounter = 1;
        startDayTimer();

    }

    private void startDayTimer(){
        if (dayOver) {
            System.out.println("derzeitiger Tag: " + dayCounter);
            dayCounter++;
        }
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        if (dayOver){
                            dayOver = !dayOver;
                        }else {
                            dayOver = true;
                        }
                        startDayTimer();
                    }
                }, 2*1000);
    }


    public boolean isDayOver() {
        return dayOver;
    }
    public int getDayCounter(){
        return dayCounter;
    }
}
