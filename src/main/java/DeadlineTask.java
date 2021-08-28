import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDate time;

    public DeadlineTask(String description, String time) {
        super(description);
        this.type = "D";
        this.time = LocalDate.parse(time);
    }

    public DeadlineTask(String description, boolean isDone, String time) {
        super(description, isDone);
        this.type = "D";
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
