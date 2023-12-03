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
     * returns event, modified server list, customer list.
     * @param svrList original server list
     * @param custList original customer list
     */
    public Pair<Event, Pair<ImList<Server>, ImList<Customer>>> 
        execute(ImList<Server> svrList, ImList<Customer> custList) {
        //customer goes to first free queue NOT shortest queue
        //int minQ = -1;
        //int minC = Integer.MAX_VALUE;
        for (int i = 0; i < svrList.size(); i++) {
            Server s = svrList.get(i);
            if (s.isFree(super.eventTime) && s.isQEmpty()) {
                svrList = svrList.set(i, s.addQ(this.c.getID()));
                return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
                    new ServeEvent(super.c, i + 1, super.eventTime), 
                        new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
            } //else {
            //minQ = (svrList.get(i).getQLength() < minC ? i : minQ);
            //}
        }
        for (int i = 0; i < svrList.size(); i++) {
            Server s = svrList.get(i);
            if (!s.isQFull()) {
                svrList = svrList.set(i, s.addQ(this.c.getID()));
                return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
                    new WaitEvent(super.c, i + 1, super.eventTime),
                        new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
            }
        }
        //if (minQ != -1) {
        //    svrList = svrList.set(minQ, svrList.get(minQ).addQ(this.c.getID()));
        //    return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
        //        new WaitEvent(super.c, minQ + 1, super.eventTime), 
        //            new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
        //} 
        return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
                new LeaveEvent(super.c, super.eventTime), 
                    new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
    }

    @Override
    public String toString() {
        return this.c.toString();
    }
}
