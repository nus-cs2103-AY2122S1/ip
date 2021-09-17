package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Represents Deadlines added to TaskList. Extends Task.
 */
public class Deadline extends Task {
    private boolean done;
    private String taskString;
    private LocalDate deadline;

    public Deadline(String taskString, LocalDate deadline) {
        this.taskString = taskString;
        this.done = false;
        this.deadline = deadline;
    }

    public void markAsDone() {
        this.done = true;
    }

    public String getTaskString() {
        if(done) {
            return "[D][X] " + this.taskString +
                    " (by: " +
                    this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D][ ] " + this.taskString + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
