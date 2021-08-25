package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private DateTimeFormatter printOut = DateTimeFormatter.ofPattern("MMM dd, E, yyyy");
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description, Task.Type.D);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, Task.Type.D, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + printOut.format(deadline) + ")";
    }

    public String toFileString() {return super.toFileString() + " " + deadline.toString();}
}
