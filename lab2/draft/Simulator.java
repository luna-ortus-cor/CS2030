import java.util.Optional;
import java.util.Comparator;

public class Simulator {
    private final int numSvr;
    private final int numCust;
    private final ImList<Double> arriveTime;
    private final ImList<Double> svcTime;

    /**
     * constructor for simulator class.
     * @param numSvr number of server
     * @param arriveTime imlist of arrival time 
     * @param svcTime imlist of service time
     */
    public Simulator(int numSvr, ImList<Double> arriveTime, ImList<Double> svcTime) {
        this.numSvr = numSvr;
        this.arriveTime = arriveTime;
        this.svcTime = svcTime;
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
        Pair<Pair<Event, ImList<Server>>, Optional<String>> peso;
        while (!pq.isEmpty()) {
            pepq = pq.poll();
            pq = pepq.second();
            peso = pepq.first().execute(svrList);
            if (pepq.first() != peso.first().first()) {
                pq = pq.add(peso.first().first());
            } else {
                if (peso.first().first().getCust().getState() == 1) {
                    numServe++;
                } else {
                    numLeave++;
                }
            }
            svrList = peso.first().second();
            if (peso.second().isPresent()) {
                output += peso.second().get();
            }
            
        }
        output += String.format("[%d %d]", numServe, numLeave);
        return output;
    }
    
    /**
     * initialize customer list.
     */
    private ImList<Customer> initCust() {
        ImList<Customer> custList = new ImList<Customer>();
        for (int i = 0; i < this.numCust; i++) {
            Customer newC = new Customer(i + 1, this.arriveTime.get(i), this.svcTime.get(i));
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
            Server newS = new Server(i);
            svrList = svrList.add(newS);
        }
        return svrList;
    }
}
