package duke.Task;
import java.time.LocalDate;

/**
 * Type of task that has description and date
 */
public class Event extends Task {
    private LocalDate dateOfEvent;
    public Event(String description, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.dateOfEvent = date;
    }

    @Override
    public boolean contains(String keyword) {
        return super.contains(keyword) || this.dateOfEvent.toString().contains(keyword);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateOfEvent + ")";
    }

    @Override
    public int compareTo(Task o) {
        return this.dateOfEvent.compareTo(o.getDate());
    }

    @Override
    public LocalDate getDate(){
        return this.dateOfEvent;
    }
}
