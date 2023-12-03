class ServeEvent extends Event {
    ServeEvent(int cID, int sID, double eventTime, ImList<Server> sList,
            ImList<Queue<Integer>> qList, ImList<Customer> cList) {
        super(cID, sID, eventTime, sList, qList, cList);
    }

    Pair<Event, Event> execute(ImList<Server> sList, ImList<Queue<Integer>> qList, 
            ImList<Customer> cList, int numS, int numSC) {
        Customer c = cList.get(super.cID - 1);
        double svcTime = c.getSupplierServeTime();
        Server s = sList.get(super.sID - 1);
        int idx = super.sID <= numS ? super.sID - 1 : qList.size() - 1; 
        Queue<Integer> q = qList.get(idx);
        if (super.sID > numS) {
            for (int i = numS; i < numS + numSC; i++) {
                if (sList.get(i).isFree(super.eventTime)) {
                    s = sList.get(i);
                    break;
                }
            }
        } else {
            s = sList.get(super.sID - 1);
        }
        Customer newC = new Customer(c, svcTime, super.eventTime + svcTime, 1);
        Server newS = new Server(s, newC.getEndTime());
        Queue<Integer> newQ = q.pop();
        cList = cList.set(super.cID - 1, newC);
        sList = sList.set(newS.getID() - 1, newS);
        qList = qList.set(idx, newQ);
        return new Pair<Event, Event>(
                new ServeEvent(super.cID, newS.getID(), super.eventTime, 
                    sList, qList, cList),
                new DoneEvent(newC.getID(), newS.getID(), newC.getEndTime(), 
                    sList, qList, cList));
    }

    @Override
    public String toString() {
        return super.sID < this.qList.size() ? 
            String.format("%.3f %d serves by %d\n", super.eventTime, super.cID, super.sID) :
            String.format("%.3f %d serves by self-check %d\n", super.eventTime, super.cID, 
                    super.sID);
    }
}
