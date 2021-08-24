import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String taskType = "D";
    private LocalDateTime date;

    public Deadline(String deadline, LocalDateTime date) {
        super(deadline);
        this.date = date;
    }


    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)",
                this.taskType, super.toString(),dateToString(this.date));
    }
}
