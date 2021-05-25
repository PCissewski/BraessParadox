import java.util.List;

public class Car implements Runnable {
    private final List<Road> route;
    private AverageTime averageObject;

    public Car(List<Road> route, AverageTime averageObject) {
        this.route = route;
        this.averageObject = averageObject;
    }

    public void run() {
        long travel_tic = System.currentTimeMillis();
        for (Road road : this.route) {
            long tic = System.currentTimeMillis();
            road.joinRoad();

            try {
                Thread.sleep(road.getDriveTime());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            road.leaveRoad();
            road.calculateAverageTime((double) (System.currentTimeMillis() - tic));
        }

        this.averageObject.recalculateAverageTime((double) (System.currentTimeMillis() - travel_tic));
    }
}
