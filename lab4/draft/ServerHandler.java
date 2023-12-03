class ServerHandler {
    private final ImList<Server> svrList;
    private final int numSvr;

    ServerHandler(int numSvr, int maxCap, Supplier<Double> restTime) {
        this.numSvr = numSvr;
        ImList<Server> tempSvr = new ImList<Server>();
        for (int i = 1; i <= this.numSvr; i++) {
            Server newS = new Server(i, new Queue(
                        new ImList<Integer>(), maxCap, restTime));
            tempSvr = tempSvr.add(newS);
        }
        this.svrList = tempSvr;
    }

    int getFree(double eventTime) {
        for (int i = 0; i < this.svrList.size(); i++) {
            Server s = this.svrList.get(i);
            if (s.isFree(eventTime) && s.isQEmpty()) {
                return i;
            }
        }
        return -1;
    }

    ServerHandler addToQ(int idx, int cID) {
        return new ServerHandler(this.svrList.set(idx, );
    }
}
