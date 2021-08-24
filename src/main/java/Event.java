import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {

    private final LocalDateTime dateTime;

    Event(String eventName, LocalDateTime dateTime) {
        super(eventName);
        this.dateTime = dateTime;
    }

    private Event(Event oldEvent) {
        super(oldEvent);
        this.dateTime = oldEvent.dateTime;
    }
    
    static protected Event createTask(String name, boolean isCompleted, LocalDateTime dateTime) {
        Event e = new Event(name, dateTime);
        if (isCompleted) {
            return new Event(e);
        } else {
            return e;
        }
    }

    @Override
    Event markAsCompleted() {
        return new Event(this);
    }

    @Override
    public String toString() {
        return "E: " + super.toString() + " on: " + this.dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy," +
                " HH:mm"));
    }
}
