package duke.task;

import duke.DukeException;
import duke.Parser;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public LocalDate getTime() {
        return this.by;
    }

    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() + " (by: " + Parser.parseLocalDate(this.by) + ")";
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;

            // Check if done status, description and time are the same.
            boolean isDoneStatusSame = this.isDone == other.isDone;
            boolean isDescriptionSame = this.description.equals(other.description);
            boolean isTimeSame = this.by.equals(other.by);

            return (isDoneStatusSame && isDescriptionSame && isTimeSame);
        }
        return false;
    }
}
