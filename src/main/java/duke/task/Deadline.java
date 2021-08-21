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
            return (this.isDone == other.getIsDone() &&
                    this.description.equals(other.getDescription()) &&
                    this.by.equals(other.getTime()));
        }
        return false;
    }
}
