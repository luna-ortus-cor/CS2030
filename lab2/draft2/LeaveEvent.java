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
     * return event, modified server list, string ouput.
     * @param svrList original server list
     */
    public Pair<Pair<Event, ImList<Server>>, String> execute(ImList<Server> svrList) {
        return new Pair<Pair<Event, ImList<Server>>, String>(
            new Pair<Event, ImList<Server>>(this, svrList),
                this.toString());
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves\n", super.eventTime, super.c.getID());
    }
}
