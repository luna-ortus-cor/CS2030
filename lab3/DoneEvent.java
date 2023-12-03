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
        svrList = svrList.set(s.getID() - 1, new Server(s.getID(), s.getQ()));
        return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
            this, new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
        //int nextC = s.peekQ(); 
        //if (nextC == -1) {
        //    svrList = svrList.set(s.getID() - 1, new Server(s.getID(), s.getQ()));
        //    return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
        //        this, new Pair<ImList<Server>, ImList<Customer>>(
        //            svrList, custList));
        //} else {
        // svrList will set s to a "free" server 
        // but this will immediately be followed by a serve event
        //svrList = svrList.set(s.getID() - 1, new Server(s.getID(), s.getQ()));
        //return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
        //new ServeEvent(custList.get(nextC - 1), s.getID(), super.eventTime), 
        //new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
        //}
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d\n",
            super.eventTime, super.c.getID(), super.sID);
    }
}
