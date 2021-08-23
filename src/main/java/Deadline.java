import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.deadlineDate = date;
        this.deadlineTime = time;
        this.dueDate = date.toString() + " " + time.toString();
        this.taskType = "D";
    }

    public Deadline(String description, LocalDate date, LocalTime time, Boolean isDone) {
        super(description);
        this.deadlineDate = date;
        this.deadlineTime = time;
        this.dueDate = date.toString() + " " + time.toString();
        this.taskType = "D";
        this.isDone = isDone;

    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (by: " + deadlineDate.getDayOfMonth() + " " +
                deadlineDate.getMonth() + " " + deadlineDate.getYear() + " " +
                deadlineTime.toString() + ")";
    }
}