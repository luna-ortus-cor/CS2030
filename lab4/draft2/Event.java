abstract class Event {
    protected final double eventTime;
    protected final int cID;
    protected final int sID;
    protected final ImList<Server> sList;
    protected final ImList<Queue<Integer>> qList;
    protected final ImList<Customer> cList;
    
    Event(int cID, int sID, double eventTime, ImList<Server> sList,
            ImList<Queue<Integer>> qList, ImList<Customer> cList) {
        this.eventTime = eventTime;
        this.cID = cID;
        this.sID = sID;
        this.sList = sList;
        this.qList = qList;
        this.cList = cList;
    }
    /**
    Event(int cID, double eventTime, ImList<Customer> cList) {
        this.eventTime = eventTime;
        this.cID = cID;
        this.sID = -1;
        this.sList = new ImList<Server>();
        this.qList = new ImList<Queue<Integer>>();
        this.cList = cList; //new ImList<Customer>();
    }

    Event(int cID, int sID, double eventTime, ImList<Customer> cList) {
        this.eventTime = eventTime;
        this.cID = cID;
        this.sID = sID;
        this.sList = new ImList<Server>();
        this.qList = new ImList<Queue<Integer>>();
        this.cList = cList; //new ImList<Customer>();
    }*/

    double getEventTime() {
        return this.eventTime;
    }

    int getCID() {
        return this.cID;
    }

    //Customer getC() {
    //    return this.cID > 0 ? this.cList.get(this.cID - 1) : new Customer(-1); //to check
    //}

    Customer getC() {
        return this.cList.get(this.cID - 1);
    }

    int getSID() {
        return this.sID;
    }

    //Server getS() {
    //    return this.sID > 0 ? this.sList.get(this.sID - 1) : new Server(-1); //to check
    //}

    ImList<Server> getSList() {
        return this.sList;
    }

    ImList<Queue<Integer>> getQList() {
        return this.qList;
    }

    ImList<Customer> getCList() {
        return this.cList;
    }

    abstract Pair<Event, Event> execute(ImList<Server> sList,  
            ImList<Queue<Integer>> qList, ImList<Customer> cList, int numS, int numSC);
}
