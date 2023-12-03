import java.util.function.Supplier;

public class SelfCheck {
    private final int state;
    private final int sID;
    private final double endTime;
    private final Supplier<Double> restTime;
    private final Queue custQ;
    private final int numServer;

    public SelfCheck(int sID, Queue custQ, Supplier<Double> restTime, int numServer) { 
        //we should instantiate with 1-q for selfcheck in simulator
        this.sID = sID;
        this.custQ = custQ;
        this.restTime = restTime;
        this.endTime = 0;
        this.state = 0;
        this.numServer = numServer;
    }
    public SelfCheck(SelfCheck other, Queue newQ) {
        this.sID = other.sID;
        this.endTime = other.endTime;
        this.custQ = newQ;
        this.restTime = other.restTime;
        this.state = other.state;
        this.numServer = other.numServer;
    }

    public SelfCheck(SelfCheck other, double endTime) {
        this.sID = other.sID;
        this.endTime = endTime;
        this.state = 1;
        this.custQ = other.custQ;
        this.restTime = other.restTime;
        this.numServer = other.numServer;
    }

    public int getID() {
        return this.sID - this.numServer;
    }

    public Queue getQ() {
        return this.custQ;
    }
    
    @Override
    public double getSupplierRestTime() {
        return 0;
    }

    @Override
    public Pair<Integer, Server> serve() {
        // have to read modified 1-q for all selfchecks
    }
}
