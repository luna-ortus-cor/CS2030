public class ArriveEvent extends Event {
    
    /**
     * constructor for arriveEvent.
     * @param c customer object
     * @param eventTime event time
     */
    public ArriveEvent(Customer c, double eventTime) {
        super(c, eventTime);
    }
    
    /**
     * returns event, modified server list, string output.
     * @param svrList original server list
     */
    public Pair<Pair<Event, ImList<Server>>, String> execute(ImList<Server> svrList) {
        for (int i = 0; i < svrList.size(); i++) {
            if (svrList.get(i).isFree(super.eventTime)) {
                svrList = svrList.set(i, new Server(i + 1, super.c.getEnd()));
                return new Pair<Pair<Event, ImList<Server>>, String>(
                    new Pair<Event, ImList<Server>>(
                        new ServeEvent(super.c, i + 1, super.eventTime), svrList),
                            this.toString());
            }
        }
        return new Pair<Pair<Event, ImList<Server>>, String>(
            new Pair<Event, ImList<Server>>(
                new LeaveEvent(super.c, super.eventTime), svrList),
                    this.toString());
    }

    @Override
    public String toString() {
        return this.c.toString();
    }
}
