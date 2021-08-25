import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public Deadline(String description, String by){
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}