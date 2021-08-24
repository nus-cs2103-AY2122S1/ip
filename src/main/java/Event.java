import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a
 * event task with a specified schedule.
 *
 * @author Pawandeep Singh
 * @version Level-4
 *
 * */
public class Event extends Task{
    private LocalDateTime schedule;

    public Event(String task, LocalDateTime schedule) {
        super(task);
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at:"+ this.schedule.format(DateTimeFormatter.ofPattern("MM dd yyyy, hh:mma")) +")";
    }
}
