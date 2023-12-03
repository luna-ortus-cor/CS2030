public class Server {
    private final int state;
    private final int sID;
    private final double tEnd;
    
    /**
     * constructor for server.
     * @param sID server ID
     */
    public Server(int sID) {
        this.sID = sID;
        this.state = 0;
        this.tEnd = 0;
    }

    /**
     * constructor for server.
     * @param sID server ID
     * @param tEnd end time
     */
    public Server(int sID, double tEnd) {
        this.sID = sID;
        this.state = 1;
        this.tEnd = tEnd;
    }

    /*
     * get server ID
     */
    public int getID() {
        return this.sID;
    }

    /*
     * override toString
     */
    @Override
    public String toString() {
        return String.format("server %d state %d busy until %f", this.sID, this.state, this.tEnd);
    }

    /**
     * check if server is free given time.
     * @param t current time
     */
    public boolean isFree(double t) {
        if (this.state == 0) {
            return true;
        } else if (this.state == 1 && this.tEnd < t) { //should this be < or <= ?
            return true;
        } else {
            return false;
        }
    }

    /**
     * get free server (or return this if not free) to update server list.
     * @param tCurr current time
     * @param tEnd end time
     */
    public Server serve(double tCurr, double tEnd) {
        if (this.state == 0) {
            return new Server(this.sID, tEnd);
        } else if (this.state == 1 && this.tEnd <= tCurr) {
            return new Server(this.sID, tEnd);
        } else {
            return this;
        }
    }
}
