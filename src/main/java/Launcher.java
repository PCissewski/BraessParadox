import java.util.ArrayList;
import java.util.List;

public class Launcher {
    private static final Road AX = new Road("AX", 100, 50);
    private static final Road XB = new Road("XB", 10, 10);
    private static final Road AY = new Road("AY", 10, 10);
    private static final Road YB = new Road("YB", 100, 50);
    private static final Road YX = new Road("YX", 100, 10);
    private static final AverageTime globalAverage = new AverageTime();

    public static void main(String[] args) throws InterruptedException {
        boolean isYX = true;
        ArrayList<Thread> threads = new ArrayList();

        for (int i = 0; i < 2500; i++) {
            for (int j = 0; j < 4; j++) {
                Thread t = new Thread(new Car(getFastestRoute(isYX), globalAverage));
                t.start();
                threads.add(t);
            }

            Thread.sleep(0, 250);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.print(globalAverage.getAverage());
        System.out.println(" is YX: " + isYX);
    }

    private static List<Road> getFastestRoute(boolean withYX) {
        double min = AX.getAverageTime() + XB.getAverageTime();
        List<Road> Route = List.of(AX, XB);
        if (AY.getAverageTime() + YB.getAverageTime() < min) {
            min = AY.getAverageTime() + YB.getAverageTime();
            Route = List.of(AY, YB);
        }

        if (withYX && AY.getAverageTime() + YX.getAverageTime() + XB.getAverageTime() < min) {
            Route = List.of(AY, YX, XB);
        }

        return Route;
    }


}
