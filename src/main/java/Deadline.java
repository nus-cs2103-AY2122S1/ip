import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String dateString = by.format(format);
        return "[D]" + super.toString() + " (by: " + dateString + ")";
    }

    @Override
    String printFormat() {
        String[] info = {"D", this.isDone ? "1" : "0", this.description, this.by};
        return String.join(" | ", info);
    }
}
