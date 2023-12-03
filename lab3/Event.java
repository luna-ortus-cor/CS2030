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
     * return server id.
     */
    public int getServ() {
        return this.sID;
    }

    /**
     * return event time.
     */
    public double getEventTime() {
        return this.eventTime;
    }
    
    /**
     * excecute/handle event method.
     * returns event, modified server and customer list
     * @param svrList server list
     * @param custList customer list
     */
    protected abstract Pair<Event, Pair<ImList<Server>, ImList<Customer>>> 
        execute(ImList<Server> svrList, ImList<Customer> custList);
}
