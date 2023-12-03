public class ArriveEvent extends Event {
    
    /**
     * constructor for arriveEvent.
     * @param c customer object
     * @param eventTime event time
     */
    public ArriveEvent(int cID, int sID, double eventTime, ServerHandler sh, SelfCheckHandler sch) {
        super(cID, sID, eventTime, sh, sch);
    }

    public ArriveEvent(ArriveEvent e, ServerHandler sh, SelfCheck Handler sch) {
        super(e.cID, e.sID, e.eventTime, sh, sch);
    }
    
    /**
     * returns event, modified server list, customer list.
     * @param svrList original server list
     * @param custList original customer list
     */
    public Pair<Event, Event> execute(ServerHandler sh, SelfCheckHandler sch,
            ImList<Customer> custList) {
        int sID = sh.getFree(super.eventTime);
        
        for (int i = 0; i < svrList.size(); i++) {
            Server s = svrList.get(i);
            if (s.isFree(super.eventTime) && s.isQEmpty()) { //need to modify qcheck method
                svrList = svrList.set(i, s.addQ(super.cID));
                //return new return type P<E,E>
                return new Pair<Event, Event>(
                        new ArriveEvent(this, sh, sch);
                        new ServeEvent(super.cID, s.getID(), super.eventTime));
            } 
        }
        for (int i = 0; i < scList.size(); i++) {
            SelfCheck sc = scList.get(i);
            if (sc.isFree(super.eventTime) && {//how check q?
                scList = scList.set(i, sc.addQ(super.cID)); //what to set?
                //return new return type P<E,E>
                return new Pair<Event, Event>(this, 
                        new ServeEvent(super.cID, s.getID(), super.eventTime));
            }
        for (int i = 0; i < svrList.size(); i++) {
            Server s = svrList.get(i);
            if (!s.isQFull()) {
                svrList = svrList.set(i, s.addQ(super.cID));
                return new Pair<Event, Event>(this,
                        new WaitEvent(super.cID, s.getID(), super.eventTime));
            }
        }
        for (int i = 0; i < scList.size(); i++) {
            SelfCheck sc = scList.get(i, sc.addQ(super.cID);
            if (!sc.isQFull()) {
                scList = scList.set(i, sc.addQ(super.cID));
                return new Pair<Event, Event>(this,
                        new WaitEvent(super.cID, sc.getID(), super.eventTime));
            }

        return new Pair<Event, Event>(this,
            new LeaveEvent(super.cID, super.eventTime));
    }

    @Override
    public String toString() {
        return this.c.toString();
    }
}
