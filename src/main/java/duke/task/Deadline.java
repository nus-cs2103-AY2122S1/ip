package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String endTime;

    public Deadline(String str, LocalDateTime endTime) {
        super(str, endTime);
        DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        this.endTime = dateOnly.format(endTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return this.endTime.equals(other.endTime);
    }
}
