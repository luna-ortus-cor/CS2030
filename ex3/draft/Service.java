class Service {
    private final Loader loader;
    private final Cruise cruise;

    Service(Loader loader, Cruise cruise) {
        this.loader = loader;
        this.cruise = cruise;
    }

    @Override
    public String toString() {
        return String.format("%s serving %s", this.loader.toString(), this.cruise.toString());
    }

    public int getServiceStartTime() {
        return this.cruise.getArrivalTime();
    }

    public int getServiceEndTime() {
        return this.cruise.getArrivalTime() + this.cruise.getServiceTime();
    }

    public int getLoaderID() {
        return this.loader.getID();
    }
}
