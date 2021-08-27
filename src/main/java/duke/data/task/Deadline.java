package duke.data.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    private final DateTimeFormatter input = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd h:mm a");

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String dateFormatter(String by) {
        LocalDateTime date = LocalDateTime.parse(by, input);
        return date.format(output);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormatter(by) + ")";
    }
}
