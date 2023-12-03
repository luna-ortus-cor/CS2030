import java.util.Comparator;
import java.util.function.Supplier;

class Simulator {
    private final int numS;
    private final int numSC;
    private final int numC;
    private final int maxCap;
    private final ImList<Double> arriveTime;
    private final Supplier<Double> serveTime;
    private final Supplier<Double> restTime;

    Simulator(int numS, int numSC, int maxCap, ImList<Double> arriveTime, 
            Supplier<Double> serveTime, Supplier<Double> restTime) {
        this.numS = numS;
        this.numSC = numSC;
        this.maxCap = maxCap;
        this.arriveTime = arriveTime;
        this.serveTime = serveTime;
        this.restTime = restTime;
        this.numC = this.arriveTime.size();
    }
    
    public String simulate() {
        State state = new State(this.numS, this.numSC, this.maxCap, this.numC,
                this.arriveTime, this.serveTime, this.restTime);

        while (!state.emptyPQ()) {
            state = state.nextState();
        }
        
        return state.getOutput();
    }
}
