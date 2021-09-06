package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends SingleTimedTask {

    /**
     * The constructor for an Event object.
     *
     * @param description The description of the Event.
     * @param date The date of the Event.
     */
    public Event(String description, LocalDate date) {
        super(description, date);
        this.taskType = "E";
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += String.format(" (at: %s)", this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        return str;
    }

    @Override
    public String getDataString() {
        String str = super.getDataString();
        return String.format("%s_~_%s", str, this.date.format(DateTimeFormatter.ofPattern("d/MM/yyyy")));
    }

    @Override
    public boolean equals(Object object) {
        Event otherEvent = (Event) object;
        return this.date.equals(otherEvent.date)
                && this.getStatusIcon().equals(otherEvent.getStatusIcon())
                && this.description.equals(otherEvent.description)
                && this.taskType.equals(otherEvent.taskType);
    }

}
