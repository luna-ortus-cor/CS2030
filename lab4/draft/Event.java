public abstract class Event {
    protected final double eventTime;
    protected final int cID;
    protected final int sID;
    protected final ImList<Server> svrList;
    protected final ImList<SelfCheck> scList;
    protected final ImList<Queue> svrQ;
    protected final ImList<Queue> scQ;
    protected final ImList<Customer> custList;
    
    /**
     * constructor for event.
     * @param c customer
     * @param eventTime event time
     */
    Event(int cID, int sID, double eventTime, ImList<Server> svrList, ImList<SelfCheck> scList,
            ImList<Queue> svrQ, ImList<Queue> scQ, ImList<Customer> custList) {
        this.cID = cID;
        this.sID = sID;
        this.eventTime = eventTime;
        this.svrList = svrList;
        this.scList = scList;
        this.svrQ = svrQ;
        this.scQ = scQ;
        this.custList = custList;
    }

    Event(int cID, double eventTime) {
        this.cID = cID;
        this.sID = -1;
        this.eventTime = eventTime;
        this.svrList = new ImList<Server>();
        this.scList = new ImList<SelfCheck>();
        this.svrQ = new ImList<Queue>();
        this.scQ = new ImList<Queue>();
        this.custList = new ImList<Customer>();
    }   
    
    /**
     * alternate constructor for event.
     * @param c customer
     * @param sID server id
     * @param eventTime event time
     */
    Event(int cID, int sID, double eventTime) {
        this.cID = cID;
        this.sID = sID;
        this.eventTime = eventTime;
        this.svrList = new ImList<Server>();
        this.scList = new ImLIst<SelfCheck>();
        this.svrQ = new ImList<Queue>();
        this.scQ = new ImList<Queue>();
        this.custList = new ImList<Customer>();
    }

    /**
     * return customer id.
     */
    public int getCID() {
        return this.cID;
    }

    /**
     * return server id.
     */
    public int getSID() {
        return this.sID;
    }

    /**
     * return event time.
     */
    public double getEventTime() {
        return this.eventTime;
    }

    public ImList<Server> getSvrList() {
        return this.svrList;
    }

    public ImList<SelfCheck> getSCList() {
        return this.scList;
    }

    public ImList<Queue> getSvrQ() {
        return this.svrQ;
    }

    public ImList<Queue> getSCQ() {
        return this.scQ;
    }

    public ImList<Customer> getCustList() {
        return this.custList;
    }
    
    /**
     * excecute/handle event method.
     * returns event, modified server and customer list
     * @param svrList server list
     * @param custList customer list
     */
    protected abstract Pair<Event, Event> execute(ImList<Server> svrList, 
            ImList<SelfCheck> scList, ImList<Queue> svrQ, ImList<Queue> scQ,
            ImList<Customer> custList);
    //protected abstract Pair<Event, Pair<ImList<Server>, ImList<Customer>>> 
    //    execute(ImList<Server> svrList, ImList<Customer> custList);
}
