package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime due;

    public Deadline(String taskName, LocalDateTime due, boolean isDone) {
        super(taskName, isDone);
        this.due = due;
    }

    public String displayTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");
        return "D " + super.displayTask() + "| " + due.format(formatter);
    }
}
