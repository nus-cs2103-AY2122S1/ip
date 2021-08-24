import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime when;

    public Event(String description, LocalDateTime when) {
        super(description);
        this.when = when;
    }

    @Override
    public String checkStatus() {
        return "[E]" + (isDone ? "[X]" : "[ ]") + " " + this.showDescription() + " " + this.showWhen();
    }

    public String showWhen(){
        return String.format("(at: %s)", when.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
    }
}
