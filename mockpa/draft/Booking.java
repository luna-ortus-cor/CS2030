class Booking implements Comparable<Booking> {
    private final Driver d;
    private final Request r;
    private final String s;
    private final double cost;

    Booking(Driver d, Request r) {
        this.d = d;
        this.r = r;
        this.s = this.d.getChoice(this.r);
        this.cost = this.d.getBest(this.r);
    }

    Booking(Driver d, double cost, String s) {
        this.d = d;
        this.r = new Request(-1, -1, -1);
        this.cost = cost;
        this.s = s;
    }

    @Override
    public int compareTo(Booking other) {
        if (this.cost < other.cost) {
            return -1;
        } else if (this.cost > other.cost) {
            return 1;
        } else {
            if (this.d.getWaitTime() < other.d.getWaitTime()) {
                return -1;
            } else if (this.d.getWaitTime() > other.d.getWaitTime()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    @Override 
    public String toString() {
        return this.s;
    }
}
