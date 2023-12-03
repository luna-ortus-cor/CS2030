import java.util.function.Supplier;

class State {
    private final ImList<Server> sList;
    private final ImList<Queue<Integer>> qList;
    private final ImList<Customer> cList;
    private final PQ<Event> pq;
    private final ImList<Double> arriveTime;
    private final Supplier<Double> serveTime;
    private final Supplier<Double> restTime;
    private final int numS;
    private final int numSC;
    private final int numC;
    private final int maxCap;
    private final String output;

    State(int numS, int numSC, int maxCap, int numC, ImList<Double> arriveTime,
            Supplier<Double> serveTime, Supplier<Double> restTime) {
        this.numS = numS;
        this.numSC = numSC;
        this.numC = numC;
        this.maxCap = maxCap;
        this.arriveTime = arriveTime;
        this.serveTime = serveTime;
        this.restTime = restTime;
        this.output = "";
        
        ImList<Queue<Integer>> tempQList = new ImList<Queue<Integer>>();
        for (int i = 0; i <= this.numS; i++) { 
            tempQList = tempQList.add(new Queue<Integer>(this.maxCap));
        }
        this.qList = tempQList;

        ImList<Server> tempSList = new ImList<Server>();
        for (int i = 0; i < this.numS + this.numSC; i++) {
            Server s = new Server(i + 1, this.restTime);
            tempSList = tempSList.add(s);
        }
        this.sList = tempSList;

        ImList<Customer> tempCList = new ImList<Customer>();
        for (int i = 0; i < this.numC; i++) {
            Customer c = new Customer(i + 1, this.arriveTime.get(i), this.serveTime);
            tempCList  = tempCList.add(c);
        }
        this.cList = tempCList;
        
        PQ<Event> pq = new PQ<Event>(new EventComparator());
        for (int i = 0; i < this.arriveTime.size(); i++) {
            pq = pq.add(new ArriveEvent(this.cList.get(i).getID(), -1,  
                        this.arriveTime.get(i), this.sList, this.qList, this.cList));
        }
        this.pq = pq;
    }

    State(State s, PQ<Event> pq, ImList<Server> sList, ImList<Queue<Integer>> qList,
            ImList<Customer> cList, String output) {
        this.numS = s.numS;
        this.numSC = s.numSC;
        this.numC = s.numC;
        this.arriveTime = s.arriveTime;
        this.serveTime = s.serveTime;
        this.restTime = s.restTime;
        this.maxCap = s.maxCap;

        this.pq = pq;
        this.sList = sList;
        this.qList = qList;
        this.cList = cList;
        this.output = output;
    }

    State nextState() {
        PQ<Event> tempPQ;
        Pair<Event, PQ<Event>> pepq = this.pq.poll();
        tempPQ = pepq.second();
        Pair<Event, Event> pee = pepq.first().execute(this.sList, this.qList, this.cList,
                this.numS, this.numSC);
        if (pee.first() != pee.second() && !pee.second().isReturn()) {
            tempPQ = tempPQ.add(pee.second());
        }
        if (pee.second().isReturn()) { //return control 
            tempPQ = tempPQ.add(new ServeEvent(pee.second().getCID(), pee.second().getSID(), 
                        pee.second().getEventTime(), pee.second().getSList(), 
                        pee.second().getQList(), pee.second().getCList()));
        }
        ImList<Server> tempSList = pee.first().getSList();
        ImList<Queue<Integer>> tempQList = pee.first().getQList();
        ImList<Customer> tempCList = pee.first().getCList();
        String tempOutput = this.output + pee.first().toString();   
        return new State(this, tempPQ, tempSList, tempQList, tempCList, tempOutput);
    }

    boolean emptyPQ() {
        return this.pq.isEmpty();
    }

    String getOutput() {
        return this.output + this.getStats();
    }

    String getStats() {
        int numServe = 0;
        int numLeave = 0;
        double totalWait = 0;
        for (int i = 0; i < this.numC; i++) {
            if (this.cList.get(i).getState() == 1) {
                numServe++;
                totalWait += this.cList.get(i).getWaitTime();
            } else {
                numLeave++;
            }
        }
        double avgWait = (numServe == 0 ? 0 : totalWait / numServe);
        return String.format("[%.3f %d %d]", avgWait, numServe, numLeave);
    }
}
