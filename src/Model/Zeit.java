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
            System.out.println(" ");
            System.out.println("---------------------------------------");
            System.out.println("     Derzeitiger Tag: " + dayCounter);
            dayCounter++;
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        dayOver = !dayOver;
                        startDayTimer();
                    }
                }, 5*1000);
    }


    public boolean isDayOver() {
        return dayOver;
    }
    public int getDayCounter(){
        return dayCounter;
    }
}
