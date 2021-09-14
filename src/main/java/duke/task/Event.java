package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event type task.
 */
public class Event extends Task {


    private final LocalDate date;

    /**
     * Constructs an Event object.
     *
     * @param description description of event.
     * @param date date of event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }


    /**
     * Returns a Event object that is subclass of Task
     *
     * @param isDone      Boolean for if the task is done.
     * @param description String for the description of the Task
     * @param time        String for date, needs to be in valid string format or exception is thrown.
     * @return A Task that is a Event object.
     * @throws DateTimeException If date string is not valid.
     */
    public static Task of(boolean isDone, String description, String time) throws DateTimeException {

        Task ret = new Event(description, LocalDate.parse(time));
        return isDone ? ret.markDone() : ret;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toStorageString() {
        return String.format("%s|%s", super.toStorageString(), this.date);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)",
                this.getTaskType(),
                super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Event) {
            return ((Event) other).description.equals(this.description)
                    && ((Event) other).date.isEqual(this.date);
        }
        return false;
    }
}
