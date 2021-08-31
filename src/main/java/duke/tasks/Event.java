package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate time;
    private DateTimeFormatter printOut = DateTimeFormatter.ofPattern("MMM dd, E, yyyy");

    /**
     * Class Constructor
     * @param description Description of the Event
     * @param time Time of the event
     */
    public Event(String description, LocalDate time) {
        super(description, Task.Type.E);
        this.time = time;
    }

    /**
     * Class Constructor when Event is read from localList.txt
     * @param description Description of the Event
     * @param time Time of the event
     * @param isDone Checks if the event is done
     */
    public Event(String description, String time, boolean isDone) {
        super(description, Task.Type.E, isDone);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + printOut.format(time) + ")";
    }

    public String toFileString() {
        return super.toFileString() + " " + time.toString();
    }
}
