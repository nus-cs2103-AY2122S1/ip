package duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String detail, LocalDateTime by) {
        super(detail, "D");
        this.by = by;
    }

    public LocalDateTime getTime() {
        return by;
    }


    @Override
    public String toString() {
        String ddl = by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        return "[D]" + super.toString() + " (by: " + ddl + ")";
    }
}
