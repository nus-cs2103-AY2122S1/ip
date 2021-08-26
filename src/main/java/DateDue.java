import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * Represents the date time formating of date and time inputs.
 * 
 * @author Gordon Yit
 * @Version CS2103T Semester 1
 */
public class DateTime {
    protected LocalDateTime dateTime;
    private int day, year, hour;
    private String month;
    private final int NOON = 12;
    
    public DateTime(String deadlineDate) throws DateTimeParseException {
        dateTime = LocalDateTime.parse(deadlineDate.replace(" ", "T"));
        day = dateTime.getDayOfMonth();
        month = dateTime.getMonth().toString().substring(0, 3);
        year = dateTime.getYear();
        hour = dateTime.getHour();
    }

    /**
     * Returns string format of the datetime.
     *
     * @return a string consisting of the date followed by time.
     */
    @Override
    public String toString() {
        String timing = hour < NOON ? hour + "AM": hour - NOON + "PM";
        String dueBy = String.format("%s %s %s %s", day, month, year, timing);
        return dueBy;
    }
}
