abstract class Driver {
    private final String license;
    private final int waitTime;

    Driver(String license, int waitTime) {
        this.license = license;
        this.waitTime = waitTime;
    }

    String getLicense() {
        return this.license;
    }

    int getWaitTime() {
        return this.waitTime;
    }

    abstract String getChoice(Request r);
    
    abstract double getBest(Request r);
    
    abstract ImList<Double> allBookingsCost(Request r);

    abstract ImList<String> allBookingsString(Request r);
}
