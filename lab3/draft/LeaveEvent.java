public class LeaveEvent extends Event {

    /**
     * constructor for leave event.
     * @param c customer
     * @param eventTime event time
     */
    public LeaveEvent(Customer c, double eventTime) {
        super(c, eventTime);
    }
    
    /**
     * return event, modified server list, customer list.
     * @param svrList original server list
     * @param custList original customer list
     */
    public Pair<Event, Pair<ImList<Server>, ImList<Customer>>> 
        execute(ImList<Server> svrList, ImList<Customer> custList) {
        return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(this, 
            new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves\n", super.eventTime, super.c.getID());
    }
}
