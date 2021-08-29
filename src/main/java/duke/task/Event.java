package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents a event type task.
 */
public class Event extends Task {


    private final LocalDate time;

    /**
     * Constructs an Event object.
     *
     * @param description description of event.
     * @param time time of event.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }


    /**
     * Returns a Event object that is subclass of Task
     *
     * @param isDone      Boolean for if the task is done.
     * @param description String for the description of the Task
     * @param time        String for time, needs to be in valid string format or exception is thrown.
     * @return A Task that is a Event object.
     * @throws DateTimeException If time string is not valid.
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
    public String toDatabaseString() {
        return String.format("%s|%s", super.toDatabaseString(), this.time);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)",
                this.getTaskType(),
                super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
