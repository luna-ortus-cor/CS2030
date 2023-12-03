public class Queue { // can we generic this to Queue<E>
    private final ImList<Integer> custQ;
    private final int maxCap;

    /**
     * constructor for queue.
     * @param custQ customer queue
     * @param maxCap max q capacity
     */
    public Queue(ImList<Integer> custQ, int maxCap) {
        this.custQ = custQ;
        this.maxCap = maxCap;
    }

    /**
     * get max length of customer queue.
     */
    public int getMax() {
        return this.maxCap;
    }

    /**
     * get customer queue length.
     */
    public int lenQ() {
        return (this.custQ.size() <= this.maxCap ? this.custQ.size() : Integer.MAX_VALUE);
    }

    /**
     * return customer and queue after popping first customer.
     */
    public Pair<Integer, Queue> serve() {
        return new Pair<Integer, Queue>(custQ.get(0), new Queue(custQ.remove(0), this.maxCap));
    }

    /**
     * return first element of queue.
     */
    public int getFront() {
        if (this.custQ.size() > 0) {
            return this.custQ.get(0);
        } else {
            return -1;
        }
    }

    /**
     * check if q full.
     */
    public boolean isFull() {
        return (this.custQ.size() == this.maxCap);
    }

    /**
     * check if q empty.
     */
    public boolean isEmpty() {
        return (this.custQ.size() == 0);
    }

    /**
     * add new customer to queue.
     * @param cID customer id
     */
    public Queue add(int cID) {
        return new Queue(this.custQ.add(cID), this.maxCap);
    }
}
