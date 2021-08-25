import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDateTime by;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu HHmm");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");

    public Deadlines(String description, LocalDateTime by) {
        super(description, false);
        this.by = by;
    }

    public Deadlines(String description, String isDone, LocalDateTime by) {
        super(description, isDone == "1");
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(displayFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return "D|" + super.toSaveString() + "|" + by.format(saveFormatter);
    }
}
