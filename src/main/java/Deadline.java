import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public Deadline(String description, String by){
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    public Deadline(String num, String description, String by) {
        this(description, by);
        this.isDone = !num.equals("0");
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getFileString() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by);
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}