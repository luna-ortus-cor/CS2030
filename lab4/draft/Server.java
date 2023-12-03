import java.util.function.Supplier;

public class Server {
    protected final int state;
    protected final int sID;
    protected final double endTime;
    protected final Queue custQ;
    protected final Supplier<Double> restTime;
    
    /**
     * constructor for server.
     * @param sID server ID
     * @param custQ customer queue
     * @param restTime rest time
     */
    public Server(int sID, Queue custQ, Supplier<Double> restTime) {
        this.sID = sID;
        this.state = 0;
        this.endTime = 0;
        this.custQ = custQ;
        this.restTime = restTime;
    }

    /**
     * constructor for server.
     * @param sID server ID
     * @param endTime end time
     * @param custQ customer queue
     * @param restTime rest time
     */
    public Server(int sID, double endTime, Queue custQ, Supplier<Double> restTime) {
        this.sID = sID;
        this.state = 1;
        this.endTime = endTime;
        this.custQ = custQ;
        this.restTime = restTime;
    }

    /**
     * alternate constructor for server given server.
     * @param s other server
     */
    public Server(Server s, double endTime) {
        this.sID = s.sID;
        this.state = 1;
        this.endTime = endTime;
        this.custQ = s.custQ;
        this.restTime = s.restTime;
    }

    /**
     * get server id.
     */
    public int getID() {
        return this.sID;
    }

    /**
     * get queue.
     */
    public Queue getQ() {
        return this.custQ;
    }

    /**
     * get rester.
     */
    public double getSupplierRestTime() {
        return this.restTime.get();
    }

    /**
     * get queue length.
     */
    public int getQLength() {
        return this.custQ.lenQ();
    }

    @Override
    public String toString() {
        return String.format("server %d state %d busy until %f", 
            this.sID, this.state, this.endTime);
    }

    /**
     * check if server is free given time.
     * @param t current time
     */
    public boolean isFree(double t) {
        if (this.state == 0) {
            return true;
        } else if (this.state == 1 && this.endTime <= t && this.custQ.lenQ() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check if q empty.
     */
    public boolean isQEmpty() {
        return this.custQ.isEmpty();
    }

    /**
     * check if q full.
     */
    public boolean isQFull() {
        return this.custQ.isFull();
    }

    /**
     * peek first element of q.
     */
    public int peekQ() {
        if (!this.isQEmpty()) {
            return this.custQ.getFront();
        } else {
            return -1;
        }
    }

    /**
     * server serves first customer in queue.
     */
    public Pair<Integer, Server> serve() { 
        //the assumption is we only call this when 
        //there is a serveevent, hence custQ will never be empty
        Pair<Integer, Queue> p = this.custQ.serve();
        return new Pair<Integer, Server>(
            p.first(), new Server(this.sID, p.second()));
    }



    /**
     * update server queue.
     * @param cID customer id
     */
    public Server addQ(int cID) {
        Queue newQ = this.custQ.add(cID);
        if (this.state == 0) {
            return new Server(this.sID, newQ);
        } else {
            return new Server(this.sID, this.endTime, newQ);
        }
    }
}
