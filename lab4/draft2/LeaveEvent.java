class LeaveEvent extends Event {
    //LeaveEvent(int cID, double eventTime, ImList<Customer> cList) {
    //    super(cID, eventTime, cList);
    //}

    LeaveEvent(int cID, int sID, double eventTime, ImList<Server> sList, 
            ImList<Queue<Integer>> qList, ImList<Customer> cList) {
        super(cID, sID, eventTime, sList, qList, cList);
    }

    Pair<Event, Event> execute(ImList<Server> sList, ImList<Queue<Integer>> qList,
            ImList<Customer> cList, int numS, int numSC) {
        LeaveEvent e = new LeaveEvent(super.cID, super.sID, super.eventTime,
                sList, qList, cList);
        return new Pair<Event, Event>(e, e);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves\n", super.eventTime, super.cID);
    }
}
