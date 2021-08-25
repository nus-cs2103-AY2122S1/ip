import java.time.LocalDate;

public class Event extends Task{
    private LocalDate time;

    public Event(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName() + this.getTimeString();
    }

    public String getTimeString() {
        return "(at: " + this.time.format(Duke.getFormatter()) + ")";
    }
}
