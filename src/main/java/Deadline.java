import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime due;

    Deadline(String taskName, LocalDateTime due) {
        super(taskName);
        this.due = due;
    }

    public String displayTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");
        return "[D]" + super.displayTask() + " (by: " + due.format(formatter) + ")";
    }
}
