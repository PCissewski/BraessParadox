public class AverageTime {
    private double avg = 0.0D;
    private double n = 0.0D;

    public synchronized void recalculateAverageTime(double time) {
        if (this.n > 0.0D) {
            this.avg += (time - this.avg) / this.n;
        } else {
            this.avg = time;
        }

        ++this.n;
    }

    public synchronized double getAverage() {
        return this.avg;
    }
}
