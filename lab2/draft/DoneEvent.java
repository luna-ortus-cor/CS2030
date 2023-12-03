import java.util.Optional;

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
     * return event, modified server list, and string output.
     * @param svrList original server list
     */
    public Pair<Pair<Event, ImList<Server>>, Optional<String>> execute(ImList<Server> svrList) {
        svrList = svrList.set(super.sID - 1, new Server(super.sID - 1));
        return new Pair<Pair<Event, ImList<Server>>, Optional<String>>(
            new Pair<Event, ImList<Server>>(this, svrList),
            Optional.of(this.toString()));
    }

    @Override
    public String toString() {
        return String.format("%.3f customer %d done serving by %d\n",
            super.eventTime, super.c.getID(), super.sID);
    }
}
