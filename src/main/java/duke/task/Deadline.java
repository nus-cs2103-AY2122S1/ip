package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Represents Deadlines added to TaskList. Extends Task.
 */
public class Deadline extends Task {
    private boolean isDone;
    private String taskString;
    private LocalDate deadline;
    private int refId;

    public Deadline(String taskString, LocalDate deadline) {
        this.taskString = taskString;
        this.isDone = false;
        this.deadline = deadline;
        this.refId = -1;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public void setRefId(int n) {
        this.refId = n;
    }

    @Override
    public int getRefId() {
        return this.refId;
    }

    @Override
    public String getTaskString() {
        if(isDone) {
            return "[D][X] " + this.taskString +
                    " (by: " +
                    this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D][ ] " + this.taskString + " (by: "
                    + this.deadline.format(DateTimeFormatter
                    .ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
