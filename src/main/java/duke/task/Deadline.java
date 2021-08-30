package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String by;
    private LocalDate time;

    public Deadline(String description, String by) {
        super(description);
        this.time = LocalDate.parse(by);
        this.by = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

    }

    public Deadline(String description, String by, int done) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toStorageString() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "D | " + done + " | " +  this.description
                + " | " + this.by + "\n";
    }
}