import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * To abstract a date inputted by the user for the event or deadline tasks.
 *
 * Users will be able input different types of date format when using Duke.
 * They can be a general string such as "tomorrow" or "sunday", or a formatted date without a time
 * or a formatted date with a specific time. A duke date will abstract the possible inputs for a date
 * by the user into a single class.
 */
public class DukeDate {

    /** Possible kinds of date users will enter */
    private String dateString;
    private LocalDate date;
    private LocalDateTime dateTime;

    private DukeDate(String string) {
        dateString = string;
    }

    private DukeDate(LocalDate date) {
        this.date = date;
    }

    private DukeDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns a DukeDate based on the user's date input. If the user enters a formatted date, the method
     * will check if the user has included a time or just the date.
     *
     * @param input The user's String input for a date.
     * @param isDate Indicates if the input string should be treated as a formatted date or not based on the
     *               command keyword used.
     * @return The DukeDate based on the user's input.
     * @throws DukeInvalidDateException Throws an exception when the user enters an invalid date.
     */
    public static DukeDate of(String input, boolean isDate) throws DukeInvalidDateException {
        if (!isDate) {
            return new DukeDate(input);
        }
        String[] strComponents = input.strip().split(" ");
        if (strComponents.length > 2) {
            return null;
        } else if (strComponents.length == 2) {
            LocalDateTime dateTime = DateParser.parseDateTime(input.strip());
            return new DukeDate(dateTime);
        } else {
            LocalDate date = DateParser.parseDate(input.strip());
            return new DukeDate(date);
        }
    }

    @Override
    public String toString() {
        if (this.dateTime != null) {
            return this.dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        } else if (this.date != null) {
            return this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } else {
            return dateString;
        }
    }
}
