class BigCruise extends Cruise {
    private static final int LENGTH_PER_LOADER = 40;
    private static final int PAX_PER_SERVE_TIME = 50;

    BigCruise(String id, int arriveTime, int cruiseLength, int numPax) {
        super(id, arriveTime, (cruiseLength % LENGTH_PER_LOADER == 0 ?
                cruiseLength / LENGTH_PER_LOADER :
                cruiseLength / LENGTH_PER_LOADER + 1), (numPax % PAX_PER_SERVE_TIME == 0 ?
                numPax / PAX_PER_SERVE_TIME :
                numPax / PAX_PER_SERVE_TIME + 1));
        int numLoad = (cruiseLength % LENGTH_PER_LOADER == 0 ?
                cruiseLength / LENGTH_PER_LOADER :
                cruiseLength / LENGTH_PER_LOADER + 1);
        int serveTime = (numPax % PAX_PER_SERVE_TIME == 0 ?
                numPax / PAX_PER_SERVE_TIME :
                numPax / PAX_PER_SERVE_TIME + 1);
    }
}
