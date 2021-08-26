import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Represents the date time formating of date and time inputs.
 * 
 * @author Gordon Yit
 * @Version CS2103T Semester 1
 */
public class DateDue {
    protected LocalDate dateTime;
    private int day, year;
    private String month;
    
    public DateDue(String deadlineDate) throws DateTimeParseException {
        dateTime = LocalDate.parse(deadlineDate.replace(" ", "T"));
        day = dateTime.getDayOfMonth();
        month = dateTime.getMonth().toString().substring(0, 3);
        year = dateTime.getYear();
    }

    /**
     * Returns string format of the datetime.
     *
     * @return a string consisting of the date followed by time.
     */
    @Override
    public String toString() {
        String dueBy = String.format("%s %s %s", day, month, year);
        return dueBy;
    }

    /**
     * Retrieves the local date object.
     * 
     * return the local date of the date due.
     */
    public LocalDate getLocalDate() {
        return this.dateTime;
    }
}
