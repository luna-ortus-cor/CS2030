class ArriveEvent extends Event {
    //ArriveEvent(int cID, double eventTime, ImList<Customer> cList) {
    //    super(cID, eventTime, cList);
    //}

    ArriveEvent(int cID, int sID, double eventTime, ImList<Server> sList,
            ImList<Queue<Integer>> qList, ImList<Customer> cList) {
        super(cID, sID, eventTime, sList, qList, cList);
    }

    Pair<Event, Event> execute(ImList<Server> sList, ImList<Queue<Integer>> qList, 
            ImList<Customer> cList, int numS, int numSC) {
        for (int i = 0; i < sList.size(); i++) {
            Server s = sList.get(i);
            int idx = i < numS ? i : qList.size() - 1;
            Queue q = qList.get(idx); 
            if (s.isFree(super.eventTime) && q.isEmpty()) {
                qList = qList.set(idx, q.push(super.cID));
                return new Pair<Event, Event>(
                        new ArriveEvent(super.cID, i + 1, super.eventTime, 
                            sList, qList, cList),
                        new ServeEvent(super.cID, i + 1, super.eventTime, 
                            sList, qList, cList));
            }
        }

        for (int i = 0; i < sList.size(); i++) {
            Server s = sList.get(i);
            int idx = i < numS ? i : qList.size() - 1;
            Queue q = qList.get(idx);
            if (!q.isFull()) {
                qList = qList.set(idx, q.push(super.cID));
                return new Pair<Event, Event>(
                        new ArriveEvent(super.cID, i + 1, super.eventTime,
                            sList, qList, cList),
                        new WaitEvent(super.cID, i + 1, super.eventTime, 
                            sList, qList, cList));
            }
        }

        return new Pair<Event, Event>(
                new ArriveEvent(super.cID, -1, super.eventTime, 
                    sList, qList, cList),
                new LeaveEvent(super.cID, -1, super.eventTime, 
                    sList, qList, cList));
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives\n", super.eventTime, super.cID);
    }
}
