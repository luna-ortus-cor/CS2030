class DoneEvent extends Event {    
    DoneEvent(int cID, int sID, double eventTime, ImList<Server> sList, 
            ImList<Queue<Integer>> qList, ImList<Customer> cList) {
        super(cID, sID, eventTime, sList, qList, cList);
    }

    DoneEvent(int cID, int sID, double eventTime, ImList<Server> sList,
            ImList<Queue<Integer>> qList, ImList<Customer> cList,
            boolean pushServe, boolean isReturn) {
        super(cID, sID, eventTime, sList, qList, cList, pushServe, isReturn);
    }

    Pair<Event, Event> execute(ImList<Server> sList, ImList<Queue<Integer>> qList,
            ImList<Customer> cList, int numS, int numSC) {
        Server s = sList.get(super.sID - 1);
        double restTime = super.sID <= numS ? s.getSupplierRestTime() : 0;
        Server newS = new Server(s, super.eventTime + restTime, super.eventTime + restTime);
        sList = sList.set(super.sID - 1, newS);
        int idx = super.sID <= numS ? super.sID - 1 : qList.size() - 1;
        Queue<Integer> q = qList.get(idx);
        DoneEvent e1 = new DoneEvent(super.cID, super.sID, super.eventTime, sList, qList, cList);
        if (q.isEmpty()) {
            return new Pair<Event, Event>(e1, e1);
        }
        DoneEvent e2 = new DoneEvent(q.getFront(), super.sID, super.eventTime + restTime,
                sList, qList, cList, false, true);
        return new Pair<Event, Event>(e1, e2);
    }

    @Override
    public String toString() {
        return super.sID < this.qList.size() ? 
            String.format("%.3f %d done serving by %d\n", super.eventTime, super.cID, super.sID) :
            String.format("%.3f %d done serving by self-check %d\n", super.eventTime, super.cID,
                    super.sID);
    }
}
