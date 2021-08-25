import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");


    public Event(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName() + this.getTimeString();
    }

    public String getTimeString() {
        return "(at: " + this.time.format(formatter) + ")";
    }
}
