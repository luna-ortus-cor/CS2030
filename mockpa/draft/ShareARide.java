class ShareARide extends Service {
    private static final int FARE = 50;
    private static final int BOOKING = 0;
    private static final int SURCHARGE = 500;
    private static final int START_TIME = 600;
    private static final int END_TIME = 900;
    private static final boolean SHARE = true;

    ShareARide() {
        super(FARE, BOOKING, SURCHARGE,
                START_TIME, END_TIME, SHARE);
    }

    int computeFare(int dist, int numPax, int time) { 
        return super.computeFare(dist, numPax, time);
    }

    @Override
    public String toString() {
        return "ShareARide";
    }
}
