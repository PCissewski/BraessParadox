
public class Road {

    private int capacity;
    private int loadCounter;
    private int counter;
    private int driveTime;
    private double averageTime;
    private String name;

    public Road(String name, int capacity, int driveTime) {
        this.capacity = capacity;
        this.counter = 0;
        this.averageTime = 0.0D;
        this.driveTime = driveTime;
        this.name = name;
    }

    public void calculateAverageTime(double time) {
        if (this.counter > 0) {
            this.averageTime += (time - this.averageTime) / (double) this.counter;
        } else {
            this.averageTime = time;
        }

        ++this.counter;
    }

    public synchronized void joinRoad() {
        while (this.loadCounter >= this.capacity) {
            try {
                this.wait();
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        }

        ++this.loadCounter;
    }

    public synchronized void leaveRoad() {
        --this.loadCounter;
        this.notifyAll();
    }

    public double getAverageTime() {
        return this.averageTime;
    }

    public int getDriveTime() {
        return this.driveTime;
    }

    public String toString() {
        return "RoadQueue{name='" + this.name + "'}";
    }
}

