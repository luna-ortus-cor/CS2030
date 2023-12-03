import java.util.Comparator;
import java.util.function.Supplier;

class Customer implements Comparable<Customer> {
    private final double arriveTime;
    private final Supplier<Double> supplierTime;
    private final int cID;
    private final int state; //0 for leave, 1 for serve
    private final double serveTime;
    private final double endTime;

    Customer(int cID, double arriveTime, Supplier<Double> supplierTime) {
        this.cID = cID;
        this.arriveTime = arriveTime;
        this.supplierTime = supplierTime;
        this.endTime = 0;
        this.serveTime = 0;
        this.state = 0;
    }

    Customer(Customer c, double serveTime, double endTime, int state) {
        this.arriveTime = c.arriveTime;
        this.supplierTime = c.supplierTime;
        this.cID = c.cID;
        this.state = state;
        this.serveTime = serveTime;
        this.endTime = endTime;
    }

    int getID() {
        return this.cID;
    }

    int getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives\n", this.arriveTime, this.cID);
    }

    double getEndTime() {
        return this.endTime == 0 ? this.arriveTime + this.serveTime : this.endTime;
    }

    double getWaitTime() {
        return this.state == 0 ? 0 : this.endTime - this.serveTime - this.arriveTime;
    }

    double getServeTime() {
        return this.serveTime;
    }

    double getArriveTime() {
        return this.arriveTime;
    }

    double getSupplierServeTime() {
        return this.supplierTime.get();
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
