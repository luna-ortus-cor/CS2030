import java.util.function.Supplier;

public class State {
    private final ServerHandler sh;
    private final SelfCheckHandler sch;
    private final ImList<Customer> custList;
    private final PQ<Event> pq;
    private final ImList<Queue> svrQ;
    private final ImList<Queue> scQ;
    private final String output;

    public State(ServerHandler sh, SelfCheckHandler sch, ImList<Customer> custList, 
            PQ<Event> pq, ImList<Queue> svrQ, ImList<Queue> scQ) {
        this.sh = sh;
        this.sch = sch;
        this.custList = custList;
        this.pq = pq;
        this.svrQ = svrQ;
        this.scQ = scQ;
        this.output = "";
    }

    public State(int numSvr, int numSC, int maxCap, int numCust, ImList<Double> arriveTime,
            Supplier<Double> serveTime, Supplier<Double> restTime) {
        ServerHandler sh = new ServerHandler(numSvr, maxCap, restTime);

        SelfCheckHandler sch = new SelfCheckHandler(numSvr, numSC, maxCap, restTime);

        ImList<Customer> tempCust = new ImList<Customer>();
        for (int i = 1; i <= numCust; i++) {
            Customer newC = new Customer(i, arriveTime.get(i - 1), serveTime);
            tempCust = tempCust.add(newC);
        }
        this.custList = tempCust;

        PQ<Event> pq = new PQ<Event>(new EventComparator());
        for (int i = 0; i < this.custList.size(); i++) {
            pq = pq.add(new ArriveEvent(this.custList.get(i), arriveTime.get(i)));
        }
        this.pq = pq;

        this.svrQ = new ImList<Queue>();
        
        this.scQ = new ImList<Queue>();

        this.output = "";
    }

    public boolean emptyPQ() {
        return this.pq.isEmpty();
    }

    public State simulate() {
        // should have already asserted PQ not empty
        Pair<Event, PQ<Event>> pepq = this.pq.poll();
        this.pq = pepq.second();
        Pair<Event, Event> pee = pepq.first().execute(this.sh, this.sch);
        
    }
}
