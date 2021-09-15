package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for event.
     *
     * @param description Description of event.
     * @param from        Event starting time.
     * @param to          Event ending time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, Task.getDateTimeFormatter());
        this.to = LocalDateTime.parse(to, Task.getDateTimeFormatter());
    }

    public Event(String description, String from, String to, String reminderTime) {
        super(description, reminderTime);
        this.from = LocalDateTime.parse(from, Task.getDateTimeFormatter());
        this.to = LocalDateTime.parse(to, Task.getDateTimeFormatter());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:"
                + from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + " -- "
                + to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ")";
    }

    @Override
    public String getIcon() {
        return "E";
    }

    @Override
    public String getTaskTime() {
        return from.format(Task.getDateTimeFormatter())
                + "--"
                + to.format(Task.getDateTimeFormatter());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return event.toString().equals(this.toString());
        } else {
            return false;
        }
    }
}
