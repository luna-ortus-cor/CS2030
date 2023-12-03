class DoneEvent extends Event {
    //DoneEvent(int cID, int sID, double eventTime, ImList<Customer> cList) {
    //    super(cID, sID, eventTime, cList);
    //}
    
    DoneEvent(int cID, int sID, double eventTime, ImList<Server> sList, 
            ImList<Queue<Integer>> qList, ImList<Customer> cList) {
        super(cID, sID, eventTime, sList, qList, cList);
    }

    Pair<Event, Event> execute(ImList<Server> sList, ImList<Queue<Integer>> qList,
            ImList<Customer> cList, int numS, int numSC) {
        Server s = sList.get(super.sID - 1);
        double restTime = super.sID <= numS ? s.getSupplierRestTime() : 0;
        Server newS = new Server(s, super.eventTime + restTime, super.eventTime + restTime);
        sList = sList.set(super.sID - 1, newS);
        int idx = super.sID <= numS ? super.sID - 1 : qList.size() - 1;
        Queue<Integer> q = qList.get(idx);
        DoneEvent e = new DoneEvent(super.cID, super.sID, super.eventTime, sList, qList, cList);
        return q.isEmpty() ? new Pair<Event, Event>(e, e) :
            new Pair<Event, Event>(e,
                    new ServeEvent(q.getFront(), super.sID, super.eventTime + restTime,
                        sList, qList, cList));
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d\n", 
                super.eventTime, super.cID, super.sID);
    }
}
