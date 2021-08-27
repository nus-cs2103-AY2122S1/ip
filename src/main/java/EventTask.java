import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private LocalDate date;

    /**
     * Constructor for EventTask object.
     * @param name name of task.
     * @param isDone whether or not task is done.
     * @param date date of event.
     */
    public EventTask(String name, boolean isDone, String date) throws DateTimeException {
        super(name, isDone);
        this.date = LocalDate.parse(date);
    }

    public EventTask(String name, String date) throws DateTimeException {
        super(name);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String formatForFile() {
        return "E" + super.formatForFile() + SAVE_DATA_MARKER + this.date + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
