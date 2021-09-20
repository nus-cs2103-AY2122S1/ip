package duke.Task;
import java.time.LocalDate;
public class Event extends Task {
    private LocalDate date;
    public Event(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public boolean contains(String keyword) {
        return super.contains(keyword) || this.date.toString().contains(keyword);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }
}
