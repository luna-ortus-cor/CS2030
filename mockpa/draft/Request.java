class Request {
    private final int dist;
    private final int numPax;
    private final int time;

    Request(int dist, int numPax, int time) {
        this.dist = dist;
        this.numPax = numPax;
        this.time = time;
    }

    int computeFare(Service svc) {
        return svc.computeFare(this.dist, this.numPax, this.time);
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", this.dist, this.numPax, this.time);
    }
}
