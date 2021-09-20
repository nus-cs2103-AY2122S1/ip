package duke.Task;
import java.time.LocalDate;

/**
 * Type of task that has description and deadline
 */
public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.date + ")";
    }
}
