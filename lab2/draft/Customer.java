import java.util.Comparator;

public class Customer implements Comparable<Customer> {
    private final double arriveTime;
    private final double serveTime;
    private final int cID;
    private final int state; // 0 for leave, 1 for serve

    /**
     * constructor for customer. 
     * @param cID customer id
     * @param arriveTime arrive time
     * @param serveTime serve time
     */
    public Customer(int cID, double arriveTime, double serveTime) {
        this.arriveTime = arriveTime;
        this.serveTime = serveTime;
        this.cID = cID;
        this.state = 0;
    }

    /**
     * alternate constructor for customer.
     * @param cID customer id
     * @param arriveTime arrive time
     * @param serveTime serve time
     * @param state state
     */
    public Customer(int cID, double arriveTime, double serveTime, int state) {
        this.arriveTime = arriveTime;
        this.serveTime = serveTime;
        this.cID = cID;
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("%.3f customer %d arrives\n", this.arriveTime, this.cID);
    }

    /**
     * get end time.
     */
    public double getEnd() {
        return this.arriveTime + this.serveTime;
    }
    
    /**
     * get customer id.
     */
    public int getID() {
        return this.cID;
    }

    /**
     * get state.
     */
    public int getState() {
        return this.state;
    }

    /**
     * get arrive time.
     */
    public double getArrive() {
        return this.arriveTime;
    }

    /**
     * get serve time.
     */
    public double getServe() {
        return this.serveTime;
    }

    @Override
    public int compareTo(Customer c) {
        if (this.arriveTime < c.arriveTime) {
            return -1;
        } else if (this.arriveTime > c.arriveTime) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * success string.
     */
    public String success(int sID) {
        return String.format("%.3f customer %d served by server %d\n", 
            this.arriveTime, this.cID, sID);
    }

    /**
     * fail string.
     */
    public String fail() {
        return String.format("%.3f customer %d leaves\n", this.arriveTime, this.cID);
    }
}
