class SelfCheckHandler {
    private final ImList<SelfCheck> scList;
    private final int numSvr;
    private final int numSC;
    private final Queue scQ;

    SelfCheckHandler(int numSvr, int numSC, int maxCap, Supplier<Double> restTime) {
        this.numSvr = numSvr;
        this.numSC = numSC:
        ImList<SelfCheck> tempSC = new ImList<SelfCheck>();
        for (int i = 1; i <= this.numSC; i++) {
            SelfCheck newSC = new SelfCheck(i + this.numSvr, new Queue(
                        new ImList<Integer>(), maxCap, restTime, this.numSvr));
            tempSC = tempSC.add(newSC);
        }
        this.scList = tempSC;
        this.scQ = new Queue(new ImList<Integer>(), maxCap, restTime, this.numSvr);
    }

    SelfCheckHandler handle() { //need to identify different types of handle for each event
        // get scID from e
        // update sc with scQ
        // call serve on sc
        // update scQ with Q from sc
        // update scList with sc
    }
}
