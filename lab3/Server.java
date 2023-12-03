public class Server {
    private final int state;
    private final int sID;
    private final double endTime;
    private final Queue custQ;
    
    /**
     * constructor for server.
     * @param sID server ID
     * @param custQ customer queue
     */
    public Server(int sID, Queue custQ) {
        this.sID = sID;
        this.state = 0;
        this.endTime = 0;
        this.custQ = custQ;
    }

    /**
     * constructor for server.
     * @param sID server ID
     * @param endTime end time
     * @param custQ customer queue
     */
    public Server(int sID, double endTime, Queue custQ) {
        this.sID = sID;
        this.state = 1;
        this.endTime = endTime;
        this.custQ = custQ;
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
