import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 8. Dates and Times
 *
 * Description:
 * Encapsulates the DukeDate Class which it stores the duration/dates
 * for the deadline and event classes
 *
 * @author Keith Tan
 */
public class DukeDate {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final DateTimeFormatter officialFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

    public DukeDate(LocalDateTime startTime, LocalDateTime endTime) {

        this.startTime = startTime;
        this.endTime = endTime;

    }

    public DukeDate(LocalDateTime endTime) {
        this.endTime = endTime;
        this.startTime = LocalDateTime.now();
    }

    /**
     * Getter that returns the end date and time of the task in a string
     * format
     *
     * @return String returns the end date and time of the task in a string
     *
     */
    public String getEndTimeString() {

        return this.endTime.format(officialFormat);

    }

    @Override
    public String toString() {
        return this.startTime.format(this.officialFormat) + " to " + this.endTime.format(this.officialFormat);
    }

}
