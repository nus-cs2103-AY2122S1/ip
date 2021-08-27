package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM-d-yyyy HH:mm"))
                + ")";
    }

    @Override
    public String getIcon() {
        return "D";
    }

    @Override
    public String getTaskTime() {
        return by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
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
