package duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Method to initialize Deadlines
     * @param detail Description of deadline
     * @param by Time that the deadline is due by in LocalDateTime format
     */
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
