import java.util.function.Supplier;

class Server {
    protected final int sID;
    protected final int state; // 0 for free 1 for busy
    protected final double endTime;
    protected final Supplier<Double> restTime;
    protected final double restingTime;
    
    Server(int sID, Supplier<Double> restTime) {
        this.sID = sID;
        this.restTime = restTime;
        this.state = 0;
        this.endTime = 0;
        this.restingTime = 0;
    }
    /**
    Server(int sID, Supplier<Double> restTime, double endTime, double restingTime) {
        this.sID = sID;
        this.restTime = restTime;
        this.endTime = endTime;
        this.state = 1;
        this.restingTime = restingTime;
    }
    */
    Server(Server s, double endTime) {
        this.sID = s.sID;
        this.endTime = endTime;
        this.restTime = s.restTime;
        this.state = 1;
        this.restingTime = 0;
    }

    Server(Server s, double endTime, double restingTime) {
        this.sID = s.sID;
        this.endTime = endTime;
        this.restTime = s.restTime;
        this.state = 1;
        this.restingTime = restingTime;
    }

    int getID() {
        return this.sID;
    }

    double getSupplierRestTime() {
        return this.restTime.get();
    }

    double getEndTime() {
        return this.endTime;
    }

    boolean isFree(double t) {
        if (this.state == 0) {
            return true;
        } else if (this.state == 1 && this.endTime <= t) {
            return true;
        } else {
            return false;
        }
    }

    boolean isResting(double t) {
        if (this.restingTime == 0) {
            return false;
        } else if (this.endTime <= t) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return String.format("Server %d state %d ending %.3f", this.sID, this.state, this.endTime);
    }
}
