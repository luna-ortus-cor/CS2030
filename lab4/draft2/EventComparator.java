import java.util.Comparator;

class EventComparator implements Comparator<Event> {

    @Override
    public int compare(Event e1, Event e2) {
        return ((e1.eventTime != e2.eventTime) ?
                Double.compare(e1.eventTime, e2.eventTime) : e1.getC().compareTo(e2.getC()));
    }
}
