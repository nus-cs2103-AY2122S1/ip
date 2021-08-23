import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private String timing;
    private LocalDateTime dateTime;

    public Event(String task, String timing) {
        super(task);
        this.timing = timing;
    }

    @Override
    String printTask() {
        String result = "";
        if (super.complete) {
            result = "[E][X] ";
        } else {
            result = "[E][ ] ";
        }
        return result + super.task + "(at: " + this.timing + ")";
    }
}
