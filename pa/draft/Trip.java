class Trip {
    private final Ride ride;
    private final ImList<Ride> rides;

    public Trip(Ride ride) {
        this.ride = ride;
        this.rides = new ImList<Ride>().add(ride);
    }

    public Trip(Ride ride, ImList<Ride> rides) {
        this.ride = ride;
        this.rides = rides;
    }

    public Trip next(Ride newRide){
        if (!(this.rides.get(this.rides.size() - 1).isSameRide(newRide))) {
            return new Trip(this.ride, this.rides.add(newRide));
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (Ride r : this.rides) {
            output += r.toString();
        }
        return output;
    }

    public int fare(Fare fs) {
        return fs.computeFare(this.rides);
    }
}           
