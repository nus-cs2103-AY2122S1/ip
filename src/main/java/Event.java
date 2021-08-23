import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task{
    private String eventTime;

    public Event (String task, String eventTime) {
        super(task);
        this.eventTime = eventTime;
    }

    public Event (String task, boolean done, String eventTime) {
        super(task, done);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone()) {
            finished = "X";
        }
        return "[E]" + "[" + finished + "] " + this.getTaskName() + " (at: " + this.eventTime + ")";
    }

    @Override
    public String toStoredString() {
        int finished = this.isDone() ? 1 : 0;
        return "E | " + finished + " | " + this.getTaskName() + " | " + eventTime;
    }
}
