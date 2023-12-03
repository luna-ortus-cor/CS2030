class Cruise {
    private final String id;
    private final int arriveTime;
    private final int serveTime;
    private final int numLoad;
    private static final int MIN_PER_HOUR = 60;
    private static final int HOUR_PER_STRING = 100;

    Cruise(String id, int arriveTime, int numLoad, int serveTime) {
        this.id = id;
        this.arriveTime = arriveTime;
        this.serveTime = serveTime;
        this.numLoad = numLoad;
    }

    @Override
    public String toString() {
        return String.format("%s@%04d", this.id, this.arriveTime);
    }

    int getServiceTime() {
        return this.serveTime;
    }

    int getArrivalTime() {
        return (this.arriveTime / HOUR_PER_STRING * MIN_PER_HOUR) + 
            (this.arriveTime % HOUR_PER_STRING);
    }

    int getNumOfLoadersRequired() {
        return this.numLoad;
    }
}
