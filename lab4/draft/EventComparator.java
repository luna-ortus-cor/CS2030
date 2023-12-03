import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    
    @Override
    public int compare(Event e1, Event e2) {
        return ((e1.eventTime != e2.eventTime) ? 
            Double.compare(e1.eventTime, e2.eventTime) : e1.c.compareTo(e2.c));
    }
}
