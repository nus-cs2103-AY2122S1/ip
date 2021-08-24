import java.time.LocalDate;
import java.time.format.*;

public class Deadline extends Task {

    LocalDate dueDate;

    Deadline(String desc, LocalDate time) {
        super(desc);
        this.dueDate = time;
    }

    @Override
    public String toString() {
        return "[D]" + this.completionStatus() + desc + " (by: "
                + dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
