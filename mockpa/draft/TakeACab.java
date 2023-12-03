class TakeACab extends Service {
    private static final int FARE = 33;
    private static final int BOOKING = 200;
    private static final int SURCHARGE = 0;
    private static final int START_TIME = 0;
    private static final int END_TIME = 0;
    private static final boolean SHARE = false;

    TakeACab() {
        super(FARE, BOOKING, SURCHARGE,
                START_TIME, END_TIME, SHARE);
    }

    int computeFare(int dist, int numPax, int time) {
        return super.computeFare(dist, numPax, time);
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}
