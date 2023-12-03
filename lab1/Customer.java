import java.util.Comparator;

public class Customer implements Comparable<Customer> {
    private final double tArrive;
    private final double tServe;
    private final int cID;

    /**
     * constructor for customer. 
     * @param cID customer id
     * @param tArrive arrive time
     * @param tServe serve time
     */
    public Customer(int cID, double tArrive, double tServe) {
        this.tArrive  = tArrive;
        this.tServe = tServe;
        this.cID = cID;
    }

    /*
     * override toString
     */
    @Override
    public String toString() {
        return String.format("%.3f customer %d arrives\n", this.tArrive, this.cID);
    }

    /*
     * get end time
     */
    private double getEnd() {
        return this.tArrive + this.tServe;
    }
    
    /*
     * override compareTo
     */
    @Override
    public int compareTo(Customer c) {
        if (this.tArrive < c.tArrive) {
            return -1;
        } else if (this.tArrive > c.tArrive) {
            return 1;
        } else {
            return 0;
        }
    }

    /*
     * comparator 
     */
    protected static final Comparator<Customer> CustomerComparator = new Comparator<Customer>() {
        /*
         * override compare
         */
        @Override
        public int compare(Customer c1, Customer c2) {
            return c1.compareTo(c2);
        }
    };

    /**
     * get new server instance. 
     * @param s imlist of servers
     */
    public Server getServer(ImList<Server> s) {
        for (int i = 0; i < s.size(); i++) {
            Server newS = s.get(i).serve(this.tArrive, this.getEnd());
            if (newS != s.get(i)) {
                return newS;
            }
        }
        return new Server(-1);
    }

    /*
     * success string
     */
    public String success(int sID) {
        return String.format("%.3f customer %d served by server %d\n", this.tArrive, this.cID, sID);
    }

    /*
     * fail string
     */
    public String fail() {
        return String.format("%.3f customer %d leaves\n", this.tArrive, this.cID);
    }
}
