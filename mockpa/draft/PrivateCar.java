class PrivateCar extends Driver {
    PrivateCar(String license, int waitTime) {
        super(license, waitTime);
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away) PrivateCar", 
                super.getLicense(), super.getWaitTime());
    }

    @Override
    double getBest(Request r) {
        double svc1 = Double.valueOf(r.computeFare(new JustRide()));
        double svc2 = Double.valueOf(r.computeFare(new ShareARide()));
        return (svc1 < svc2 ? svc1 : svc2);
    }

    @Override
    String getChoice(Request r) {
        double svc1 = Double.valueOf(r.computeFare(new JustRide()));
        double svc2 = Double.valueOf(r.computeFare(new ShareARide()));
        if (svc1 < svc2) {
            return String.format("$%.2f using %s (JustRide)", svc1 / 100, this.toString());
        } else {
            return String.format("$%.2f using %s (ShareARide)", svc2 / 100, this.toString());
        }
    }

    int getWaitTime() {
        return super.getWaitTime();
    }

    ImList<Double> allBookingsCost(Request r) {
        double svc1 = r.computeFare(new JustRide());
        double svc2 = r.computeFare(new ShareARide());
        return new ImList<Double>().add(svc1).add(svc2);
    }

    ImList<String> allBookingsString(Request r) {
        double svc1 = r.computeFare(new JustRide());
        double svc2 = r.computeFare(new ShareARide());
        String s1 = String.format("$%.2f using %s (JustRide)", svc1 / 100, this.toString());
        String s2 = String.format("$%.2f using %s (ShareARide)", svc2 / 100, this.toString());
        return new ImList<String>().add(s1).add(s2);
    }
}
