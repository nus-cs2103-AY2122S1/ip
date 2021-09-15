package duke;

import java.time.LocalDate;

/**
 * Represents a specific type of Task with an additional date field.
 * @author Zhao Peiduo
 */
public class Event extends Task {
    private final LocalDate date;

    /**
     * The constructor for an Event Object.
     */
    public Event(String taskTitle, LocalDate date) {
        super(taskTitle);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Customizes the string representation of an event object.
     *
     * @return string representation of an event in the form [E][{X}] {description} (at: {date})
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.getMonth().toString() + " "
                + date.getDayOfMonth() + " " + date.getYear() + ")";
    }

    /**
     * Creates the string representation to be saved in external txt file.
     *
     * @return String representation to be saved in external txt file.
     * */
    @Override
    public String toStringRecord() {
        return "[E]" + super.toString() + "(at: " + date.toString() + ")";
    }

    /**
     * Two event objects are equal iff they are equal tasks and they have the same date.
     *
     * @param another the object to be compared with
     * */
    @Override
    public boolean equals(Object another) {
        if (another instanceof Event) {
            Event anotherTask = (Event) another;
            return super.equals(another) && date.equals(anotherTask.getDate());
        }
        return false;
    }
}
