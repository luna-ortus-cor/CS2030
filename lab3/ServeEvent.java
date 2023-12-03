public class ServeEvent extends Event {

    /**
     * constructor for serve event.
     * @param c customer
     * @param sID server id
     * @param eventTime event time
     */
    public ServeEvent(Customer c, int sID, double eventTime) {
        super(c, sID, eventTime);
    }

    /**
     * return event, server list, customer list.
     * @param svrList server list
     * @param custList customer list
     */
    public Pair<Event, Pair<ImList<Server>, ImList<Customer>>> 
        execute(ImList<Server> svrList, ImList<Customer> custList) {
        Customer c1 = super.c;
        double c1Svc = c1.getSupplier().get();
        Server s1 = svrList.get(super.sID - 1);
        Pair<Integer, Server> p = s1.serve(); //we could check that p.first is super.c
        Customer c2 = new Customer(c1.getID(), c1.getArrive(), c1.getSupplier(), 
            c1Svc, super.eventTime + c1Svc, 1);
        Server s2 = new Server(p.second().getID(), c2.getEnd(), p.second().getQ());
        custList = custList.set(c1.getID() - 1, c2);
        svrList = svrList.set(s1.getID() - 1, s2);
        return new Pair<Event, Pair<ImList<Server>, ImList<Customer>>>(
            new DoneEvent(c2, s2.getID(), c2.getEnd()),
                new Pair<ImList<Server>, ImList<Customer>>(svrList, custList));
    }

    @Override
    public String toString() {
        return String.format("%.3f %d serves by %d\n", 
            super.eventTime, super.c.getID(), super.sID);
    }
}
