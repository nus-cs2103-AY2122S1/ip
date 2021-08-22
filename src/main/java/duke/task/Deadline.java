package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task{

    private LocalDate time;

    private Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public static Task of(boolean isDone, String description, String time) throws DateTimeException {
        Task ret = new Deadline(description, LocalDate.parse(time));
        return isDone ? ret.done() : ret;
    }

    public String getTaskType() { return "D"; }

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
