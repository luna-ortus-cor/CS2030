abstract class Service {
    private final int fare;
    private final int book;
    private final int surcharge;
    private final int surchargeStart;
    private final int surchargeEnd;
    private final boolean split;

    Service(int fare, int book, int surcharge, int start, int end, boolean split) {
        this.fare = fare;
        this.book = book;
        this.surcharge = surcharge;
        this.surchargeStart = start;
        this.surchargeEnd = end;
        this.split = split;
    }

    int computeFare(int dist, int numPax, int time) {
        if (this.split == true) {
            return (this.surchargeStart <= time && time <= this.surchargeEnd ?
                (dist * this.fare + this.surcharge + this.book) / numPax :
                (dist * this.fare + this.book) / numPax);
        } else {
            return (this.surchargeStart <= time && time <= this.surchargeEnd ? 
                dist * this.fare + this.surcharge + this.book : dist * this.fare + this.book);
        }
    }
}
        
