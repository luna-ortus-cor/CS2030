public abstract class Event {
    protected final double eventTime;
    protected final Customer c;
    protected final int sID;
    
    /**
     * constructor for event.
     * @param c customer
     * @param eventTime event time
     */
    Event(Customer c, double eventTime) {
        this.c = c;
        this.eventTime = eventTime;
        this.sID = -1;
    }
    
    /**
     * alternate constructor for event.
     * @param c customer
     * @param sID server id
     * @param eventTime event time
     */
    Event(Customer c, int sID, double eventTime) {
        this.c = c;
        this.sID = sID;
        this.eventTime = eventTime;
    }

    /**
     * return customer.
     */
    public Customer getCust() {
        return this.c;
    }
    
    /**
     * excecute/handle event method.
     * NOTE: do we want to also return custList?
     * @param svrList server list
     */
    protected abstract Pair<Pair<Event, ImList<Server>>, 
        String> execute(ImList<Server> svrList);
}
