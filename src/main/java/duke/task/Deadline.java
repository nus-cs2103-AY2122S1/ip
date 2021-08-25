package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate deadline;

    public Deadline(String taskName, boolean doneStatus, LocalDate deadline) {
        super(taskName, doneStatus);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return "[D]" + super.toString() + " (by: " + this.deadline.format(dtf) + ")";
    }
}
