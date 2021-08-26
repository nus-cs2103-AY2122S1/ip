import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String taskname, String deadline) {
        super(taskname);
        this.deadline = LocalDate.parse(deadline);
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "Deadline: " + super.toString() + " (by " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
