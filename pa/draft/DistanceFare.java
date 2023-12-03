class DistanceFare implements Fare {
    private final int flatFare;
    private final int flatFareDist;
    private final int bandFare;
    private final int bandFareDist;
    private final int maxDist;

    public DistanceFare(int flatFare, int flatFareDist, int bandFare, int bandFareDist, int maxDist) {
        this.flatFare = flatFare;
        this.flatFareDist = flatFareDist;
        this.bandFare = bandFare;
        this.bandFareDist = bandFareDist;
        this.maxDist = maxDist;
    }

    @Override
    public String toString() {
        return String.format("Distance fare: %d up to %d; %d every %d up to %d",
                this.flatFare, this.flatFareDist, this.bandFare, this.bandFareDist, this.maxDist);
    }

    public int computeFare(Iterable<Ride> trip) {
        int totalFare = flatFare;
        int dist = 0;
        for (Ride r: trip) { dist += r.distance(); }
        if (true) {
            if (dist > this.maxDist + this.flatFareDist) {
                dist = this.maxDist + this.flatFareDist;
            }
            if (dist >= this.flatFareDist) {
                dist -= this.flatFareDist;
            }
            if (dist == 0) {
                return totalFare;
            }
            if (dist % this.bandFareDist == 0) {
                totalFare += (dist / bandFareDist * bandFare);
            } else {
                totalFare += (((dist / bandFareDist) + 1) * bandFare);
            }
        }
        return totalFare;
    }
}
