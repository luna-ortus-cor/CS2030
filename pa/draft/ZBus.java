class ZBus implements Ride {
    private final static int DIST = 800;
    private final String busNum;
    private final int bStage;
    private final int aStage;

    public ZBus(String busNum, int bStage, int aStage) {
        this.busNum = busNum;
        this.bStage = bStage;
        this.aStage = aStage;
    }

    public int distance() {
        return DIST * (this.aStage - this.bStage);
    }

    @Override
    public String toString() {
        return String.format("[Bus %s: %d -- %d]", this.busNum, this.bStage, this.aStage);
    }

    public String getID() {
        return this.busNum;
    }

    public int getAStn() {
        return -1;
    }

    public int getBStn() {
        return -1;
    }

    public boolean isSameRide(Ride otherRide) {
        return this.busNum.equals(otherRide.getID());
    }
}
