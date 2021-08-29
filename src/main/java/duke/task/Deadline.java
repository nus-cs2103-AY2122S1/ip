package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents a deadline type task.
 */
public class Deadline extends Task {

    private final LocalDate time;

    private Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a Deadline object that is subclass of Task
     *
     * @param isDone      Boolean for if the task is done.
     * @param description String for the description of the Task
     * @param time        String for time, needs to be in valid string format or exception is thrown.
     * @return A Task that is a Deadline object.
     * @throws DateTimeException If time string is not valid.
     */
    public static Task of(boolean isDone, String description, String time) throws DateTimeException {
        Task ret = new Deadline(description, LocalDate.parse(time));
        return isDone ? ret.markDone() : ret;
    }

    public String getTaskType() {
        return "D";
    }

    @Override
    public String toDatabaseString() {
        return String.format("%s|%s", super.toDatabaseString(), this.time);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                this.getTaskType(),
                super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
