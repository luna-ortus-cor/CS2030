import java.util.List;

ImList<Booking> findBestBooking(Request r, List<Driver> l) {
    ImList<Booking> b = new ImList<Booking>();
    for (Driver d : l) {
        ImList<Double> c = d.allBookingsCost(r);
        ImList<String> a = d.allBookingsString(r);
        for (int i = 0; i < c.size(); i++) {
            b = b.add(new Booking(d, c.get(i), a.get(i)));
        }
    }
    b = b.sort(new BookingComparator());
    return b;
}
