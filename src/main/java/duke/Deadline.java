package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getIcon() {
        return "D";
    }

    @Override
    public String getTaskTime() {
        return this.by.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return deadline.toString().equals(this.toString());
        } else {
            return false;
        }
    }
}
