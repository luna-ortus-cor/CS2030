class ZTrain implements Ride {
    private final static ImList<Integer> STN_DIST = 
        new ImList<Integer>().add(0).add(1500).add(3900).add(5100).add(6000);
    private final int bStn;
    private final int aStn;

    public ZTrain(int bStn, int aStn) {
        this.bStn = bStn;
        this.aStn = aStn;
    }

    @Override
    public String toString() {
        return String.format("[Train: %d -- %d]", this.bStn, this.aStn);
    }

    public int distance() {
        return STN_DIST.get(this.aStn - 1) - STN_DIST.get(this.bStn - 1);
    }

    public String getID() {
        return "";
    }

    public int getAStn() {
        return this.aStn;
    }

    public int getBStn() {
        return this.bStn;
    }

    public boolean isSameRide(Ride otherRide) {
        return this.aStn == otherRide.getBStn();
    }
}
