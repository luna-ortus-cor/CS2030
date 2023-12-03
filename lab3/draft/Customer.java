import java.util.Comparator;
import java.util.function.Supplier;

public class Customer implements Comparable<Customer> {
    private final double arriveTime;
    private final Supplier<Double> sup;
    private final int cID;
    private final int state; // 0 for leave, 1 for serve
    private final double endTime;
    private final double serveTime;

    /**
     * constructor for customer. 
     * @param cID customer id
     * @param arriveTime arrive time
     * @param sup supplier of double
     */
    public Customer(int cID, double arriveTime, Supplier<Double> sup) {
        this.arriveTime = arriveTime;
        this.sup = sup;
        this.cID = cID;
        this.state = 0;
        this.endTime = 0;
        this.serveTime = 0;
    }

    /**
     * alternate constructor for customer.
     * @param cID customer id
     * @param arriveTime arrive time
     * @param sup supplier of double
     * @param serveTime serve time
     * @param endTime end time
     * @param state state
     */
    public Customer(int cID, double arriveTime, Supplier<Double> sup, 
        double serveTime, double endTime, int state) {
        this.arriveTime = arriveTime;
        this.sup = sup;
        this.cID = cID;
        this.state = state;
        this.serveTime = serveTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives\n", this.arriveTime, this.cID);
    }

    /**
     * get end time.
     */
    public double getEnd() {
        if (this.endTime == 0) {
            return this.arriveTime + this.serveTime;
        } else {
            return this.endTime;
        }
    }

    /**
     * get wait time.
     */
    public double getWait() {
        if (this.state == 0) {
            return 0;
        } else {
            return this.endTime - this.serveTime - this.arriveTime;
        }
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

    /**
     * get supplier.
     */
    public Supplier<Double> getSupplier() {
        return this.sup;
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
}
