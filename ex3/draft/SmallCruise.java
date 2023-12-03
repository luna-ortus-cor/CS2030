class SmallCruise extends Cruise {
    private static final int SERVE_TIME = 30;
    private static final int NUM_LOADER = 1;

    SmallCruise(String id, int arriveTime) {
        super(id, arriveTime, NUM_LOADER, SERVE_TIME);
    }
}
