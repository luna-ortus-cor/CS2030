public class DoneEvent extends Event {

    /**
     * constructor for done event.
     * @param c customer
     * @param eventTime event time
     */
    public DoneEvent(Customer c, int sID, double eventTime) {
        super(c, sID, eventTime);
    }
    
    /**
     * return event, modified server list, customer list.
     * @param svrList original server list
     * @param custList original customer list
     */
    public Pair<Event, Pair<ImList<Server>, ImList<Customer>>> 
        execute(ImList<Server> svrList, ImList<Customer> custList) {
        Server s = svrList.get(super.sID - 1);
        double restTime = s.getSupplierRestTime();
        //svrList = svrList.set(s.getID() - 1, new Server(s.getID(),super.eventTime + restTime,  s.getQ()));
        svrList = svrList.set(s, super.eventTime + restTime);
        return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
            this, new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d\n",
            super.eventTime, super.c.getID(), super.sID);
    }
}
