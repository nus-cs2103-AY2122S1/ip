import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Event(String taskName, LocalDate date,
                 LocalTime startTime, LocalTime endTime) throws DukeException {
        super(taskName);
        // If start time is greater than or equal to end time, throw exception
        if (startTime.compareTo(endTime) >= 0) {
            throw new DukeException("Start time of event cannot be later or equal to end time.");
        }
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedStartTime = this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String formattedEndTime = this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ", "
                + formattedStartTime + " - " + formattedEndTime + ")";
    }
}
