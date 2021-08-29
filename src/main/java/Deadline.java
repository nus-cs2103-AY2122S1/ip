import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate dueDate = null;

    public Deadline(String deadlineName) {
        super(deadlineName.substring(9, deadlineName.indexOf("/by ")));
        int start = deadlineName.indexOf("/by ");
        this.dueDate = LocalDate.parse(deadlineName.substring(start + 4));
    }

    public Deadline(String deadlineName, boolean isDone) {
        super(deadlineName.substring(0, deadlineName.indexOf("(by:")), isDone);
        int start = deadlineName.indexOf("(by:") + 5;
        this.dueDate = LocalDate.parse(deadlineName.substring(start, start + 11),
                DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + "(by: "
                + this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
