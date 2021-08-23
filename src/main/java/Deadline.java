import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.deadlineDate = date;
        this.deadlineTime = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate.getDayOfMonth() + " " +
                deadlineDate.getMonth() + " " + deadlineDate.getYear() + " " +
                deadlineTime.toString() + ")";
    }
}