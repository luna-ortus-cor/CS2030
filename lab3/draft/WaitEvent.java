class WaitEvent extends Event {


    /**
     * constructor for wait event.
     * @param c customer
     * @param sID server id
     * @param eventTime event time
     */
    public WaitEvent(Customer c, int sID, double eventTime) {
        super(c, sID, eventTime);
    }
    
    /**
     * return event, modified server list, customer list.
     * @param svrList server list
     * @param custList customer list
     */
    public Pair<Event, Pair<ImList<Server>, ImList<Customer>>> 
        execute(ImList<Server> svrList, ImList<Customer> custList) {
        return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
            this, new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits at %d\n",super.eventTime,super.c.getID(),super.sID);
    }
}
    
