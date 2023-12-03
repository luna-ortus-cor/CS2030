import java.util.Comparator;
import java.util.function.Supplier;

public class Simulator {
    private final int numSvr;
    private final int numCust;
    private final int maxCap;
    private final ImList<Double> arriveTime;
    private final Supplier<Double> sup;

    /**
     * constructor for simulator class.
     * @param numSvr number of server
     * @param maxCap max q capacity
     * @param arriveTime imlist of arrival time 
     * @param sup double supplier
     */
    public Simulator(int numSvr, int maxCap, ImList<Double> arriveTime, Supplier<Double> sup) {
        this.numSvr = numSvr;
        this.maxCap = maxCap;
        this.arriveTime = arriveTime;
        this.sup = sup;
        this.numCust = this.arriveTime.size();
    }

    /**
     * runs actual simulation 
     * updating server list and
     * iterating through customer list in order of arrival time.
     */
    public String simulate() {
        int numServe = 0;
        int numLeave = 0;

        ImList<Customer> custList = initCust();
        ImList<Server> svrList = initSvr();

        PQ<Event> pq = new PQ<Event>(new EventComparator());
        for (int i = 0; i < custList.size(); i++) {
            pq = pq.add(new ArriveEvent(custList.get(i), arriveTime.get(i)));
        }
                
        String output = "";
        
        Pair<Event, PQ<Event>> pepq;
        Pair<Event, Pair<ImList<Server>, ImList<Customer>>> pepsc;
        while (!pq.isEmpty()) {
            pepq = pq.poll();
            pq = pepq.second();
            pepsc = pepq.first().execute(svrList, custList);
            svrList = pepsc.second().first();
            custList = pepsc.second().second();
            output += pepq.first().toString();
            if (pepq.first() != pepsc.first()) {
                pq = pq.add(pepsc.first());
            } else {
                int svrIdx = pepsc.first().getServ();
                double et = pepsc.first().getEventTime();
                if (pepsc.first().getServ() != -1) {
                    Server s = svrList.get(svrIdx - 1);
                    if (s.isFree(et) && s.getQLength() > 0) {
                        int custIdx = s.peekQ();
                        Customer c = custList.get(custIdx - 1);
                        pq = pq.add(new ServeEvent(c, svrIdx, et));
                    }
                }
            }
        }
        output += calcCustStats(custList);
        return output;
    }

    /**
     * calculate customer stats from cust List. // we can do something similar for servers
     * @param custList customer list
     */
    public String calcCustStats(ImList<Customer> custList) {
        int numServe = 0;
        int numLeave = 0;
        double totalWait = 0;
        for (int i = 0; i < this.numCust; i++) {
            if (custList.get(i).getState() == 1) {
                numServe++;
                totalWait += custList.get(i).getWait();
            } else {
                numLeave++;
            }
        }
        double avgWait = (numServe == 0 ? 0 : totalWait / numServe);
        return String.format("[%.3f %d %d]", avgWait, numServe, numLeave);
    }
    
    /**
     * initialize customer list.
     */
    private ImList<Customer> initCust() {
        ImList<Customer> custList = new ImList<Customer>();
        for (int i = 0; i < this.numCust; i++) {
            Customer newC = new Customer(i + 1, this.arriveTime.get(i), sup);
            custList = custList.add(newC);
        }
        return custList;
    }

    /**
     * initialize server list.
     */
    private ImList<Server> initSvr() {
        ImList<Server> svrList = new ImList<Server>();
        for (int i = 1; i <= this.numSvr; i++) {
            Server newS = new Server(i, new Queue(new ImList<Integer>(), this.maxCap));
            svrList = svrList.add(newS);
        }
        return svrList;
    }
}
