abstract class Event {
    protected final double eventTime;
    protected final int cID;
    protected final int sID;
    protected final ImList<Server> sList;
    protected final ImList<Queue<Integer>> qList;
    protected final ImList<Customer> cList;
    protected final boolean pushServe;
    protected final boolean isReturn;
    
    Event(int cID, int sID, double eventTime, ImList<Server> sList,
            ImList<Queue<Integer>> qList, ImList<Customer> cList) {
        this.eventTime = eventTime;
        this.cID = cID;
        this.sID = sID;
        this.sList = sList;
        this.qList = qList;
        this.cList = cList;
        this.pushServe = false;
        this.isReturn = false;
    }

    Event(int cID, int sID, double eventTime, ImList<Server> sList,
            ImList<Queue<Integer>> qList, ImList<Customer> cList, boolean pushServe) {
        this.eventTime = eventTime;
        this.cID = cID;
        this.sID = sID;
        this.sList = sList;
        this.qList = qList;
        this.cList = cList;
        this.pushServe = pushServe;
        this.isReturn = false;
    }

    Event(int cID, int sID, double eventTime, ImList<Server> sList,
            ImList<Queue<Integer>> qList, ImList<Customer> cList, 
            boolean pushServe, boolean isReturn) {
        this.eventTime = eventTime;
        this.cID = cID;
        this.sID = sID;
        this.sList = sList;
        this.qList = qList;
        this.cList = cList;
        this.pushServe = pushServe;
        this.isReturn = isReturn;
    }
    
    double getEventTime() {
        return this.eventTime;
    }

    int getCID() {
        return this.cID;
    }

    Customer getC() {
        return this.cList.get(this.cID - 1);
    }

    int getSID() {
        return this.sID;
    }

    ImList<Server> getSList() {
        return this.sList;
    }

    ImList<Queue<Integer>> getQList() {
        return this.qList;
    }

    ImList<Customer> getCList() {
        return this.cList;
    }

    boolean isReturn() {
        return this.isReturn;
    }

    abstract Pair<Event, Event> execute(ImList<Server> sList,  
            ImList<Queue<Integer>> qList, ImList<Customer> cList, int numS, int numSC);
}
