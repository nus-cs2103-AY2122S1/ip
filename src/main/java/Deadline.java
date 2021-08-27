import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected final LocalDateTime by;

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone, TaskType.DEADLINE);
        this.by = by;
    }

    public String getDeadlineTime(){
        return "(by: " + by.format(DateTimeFormatter.ofPattern("dd MM yyyy HHmm")) + ")";
    }

    @Override
    public String getStatus(){
        return "[D]" + super.getStatus() + " " + getDeadlineTime();
    }

    @Override
    public String toString() {
        return "D" + " | " + super.toString() + " | " + by;
    }
}