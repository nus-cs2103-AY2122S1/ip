import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate dueDate = null;

    public Deadline(String todoName) {
        super(todoName.substring(9, todoName.indexOf("/by ")));
        int start = todoName.indexOf("/by ");
        this.dueDate = LocalDate.parse(todoName.substring(start + 4));

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
