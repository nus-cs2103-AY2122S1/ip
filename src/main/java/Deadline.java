import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    public Deadline(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName() + this.getTimeString();
    }

    public String getTimeString() {
        return "(by: " + this.time.format(formatter) + ")";
    }
}
