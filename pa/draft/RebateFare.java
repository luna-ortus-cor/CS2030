class RebateFare implements Fare {
    private final StagedFare sFare;
    private final int rebate;

    public RebateFare(StagedFare sFare, int rebate) {
        this.sFare = sFare;
        this.rebate = rebate;
    }

    @Override
    public String toString() {
        return String.format("%s (with rebate %d)", this.sFare.toString(), this.rebate);
    }

    public int computeFare(Iterable<Ride> trip) {
        int ogFare = this.sFare.computeFare(trip);
        int numTransfer = 0;
        for (Ride r : trip) {
            numTransfer += 1;
        }
        int rbFare = ogFare - (rebate * (numTransfer - 1));
        return rbFare;
    }
}
