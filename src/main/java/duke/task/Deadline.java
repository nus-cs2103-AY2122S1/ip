package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate timeDue;

    public Deadline(String description, LocalDate timeDue) {
        super(description);
        this.timeDue = timeDue;
    }

    public Deadline(String description, boolean isDone, LocalDate timeDue) {
        super(description, isDone);
        this.timeDue = timeDue;
    }

    @Override
    public String taskToLine() {
        return "D" + super.taskToLine() + " | " + this.timeDue;
    }

    @Override
    public LocalDate getDate() {
        return this.timeDue;
    }

    @Override
    public String toString() {
        String description = super.toString();
        return "[D]" + description + " (by: "
                + this.timeDue.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
