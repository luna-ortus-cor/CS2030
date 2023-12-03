import java.util.Optional;

public class ServeEvent extends Event {

    /**
     * constructor for serve event.
     * @param c customer
     * @param eventTime event time
     */
    public ServeEvent(Customer c, int sID, double eventTime) {
        super(c, sID, eventTime);
    }

    /**
     * return event, server list, string output.
     * @param svrList server list
     */
    public Pair<Pair<Event, ImList<Server>>, Optional<String>> execute(ImList<Server> svrList) {
        svrList = svrList.set(super.sID - 1, new Server(super.sID - 1, super.c.getEnd())); 
        return new Pair<Pair<Event, ImList<Server>>, Optional<String>>(
            new Pair<Event, ImList<Server>>(
                new DoneEvent(new Customer(super.c.getID(), super.c.getArrive(), 
                    super.c.getServe(), 1), super.sID, super.c.getEnd()), svrList),
                        Optional.of(this.toString()));
    }

    @Override
    public String toString() {
        return String.format("%.3f customer %d serves by %d\n", 
            super.eventTime, super.c.getID(), super.sID);
    }
}
