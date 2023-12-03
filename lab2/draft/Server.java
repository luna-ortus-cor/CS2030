public class Server {
    private final int state;
    private final int sID;
    private final double endTime;
    
    /**
     * constructor for server.
     * @param sID server ID
     */
    public Server(int sID) {
        this.sID = sID;
        this.state = 0;
        this.endTime = 0;
    }

    /**
     * constructor for server.
     * @param sID server ID
     * @param endTime end time
     */
    public Server(int sID, double endTime) {
        this.sID = sID;
        this.state = 1;
        this.endTime = endTime;
    }

    /**
     * get server id.
     */
    public int getID() {
        return this.sID;
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
        } else if (this.state == 1 && this.endTime <= t) { //should this be < or <= ?
            return true;
        } else {
            return false;
        }
    }
}
