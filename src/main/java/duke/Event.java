package duke;

import java.time.LocalDate;

/**
 * Represents a specific type of Task with an additional date field.
 */
public class Event extends Task {
    private LocalDate date;

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
     * Two event objects are equal iff they are equal tasks and they have the same date.
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
