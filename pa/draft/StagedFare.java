class StagedFare implements Fare {
    private final ImList<Pair<Integer, Integer>> fare;

    public StagedFare(int dist, int amt) {
        this.fare = new ImList<Pair<Integer, Integer>>().
            add(new Pair<Integer, Integer>(dist, amt));
    }

    public StagedFare(ImList<Pair<Integer, Integer>> fare) {
        this.fare = fare;
    }

    public StagedFare add(int dist, int amt) {
        return new StagedFare(fare.add(new Pair<Integer, Integer>(dist, amt)));
    }

    @Override
    public String toString() {
        int minFare = Integer.MAX_VALUE;
        int maxFare = Integer.MIN_VALUE;
        for (Pair<Integer, Integer> p : fare) {
            if (p.second() > maxFare) {
                maxFare = p.second();
            }
            if (p.second() < minFare) {
                minFare = p.second();
            }
        }
        return String.format("Staged fare from %d to %d", minFare, maxFare);
    }

    public int computeFare(Iterable<Ride> trip) {
        int fareTotal = 0;
        for (Ride r : trip) {
            int dist = r.distance();
            int currFare = 0;
            int maxDist = Integer.MAX_VALUE;
            for (Pair<Integer, Integer> p : this.fare) {
                if (dist <= p.first() && p.first() < maxDist) {
                    maxDist = p.first();
                    currFare = p.second();
                }
            }
            fareTotal += currFare;
        }
        return fareTotal;
    }
}
