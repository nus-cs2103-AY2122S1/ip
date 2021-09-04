package duke;

import exceptions.DukeInvalidDateException;
import exceptions.DukeInvalidStorageTaskException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * To abstract a date inputted by the user for the event or deadline tasks.
 * Users will be able input different types of date format when creating tasks such as event and deadline tasks.
 * They can be a general string such as "tomorrow" or "sunday", or a formatted date without a time
 * or a formatted date with a specific time. A duke date will abstract the possible inputs for a date
 * by the user into a single class.
 */
public class DukeDate {

    /** Constants indicating the type of date a DukeDate is storing */
    public static final int HAS_STRING = 1;
    public static final int HAS_DATE = 2;
    public static final int HAS_DATE_TIME = 3;

    /** Possible kinds of date users will enter */
    private String dateString;
    private LocalDate date;
    private LocalDateTime dateTime;

    /** Stores what type of date is stored in the duke date. Based on the static constants above */
    private final int dateType;

    private DukeDate(String string) {
        dateString = string;
        this.dateType = DukeDate.HAS_STRING;
    }

    private DukeDate(LocalDate date) {
        this.date = date;
        this.dateType = DukeDate.HAS_DATE;
    }

    private DukeDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.dateType = DukeDate.HAS_DATE_TIME;
    }

    /**
     * Returns a duke.DukeDate based on the user's date input. If the user enters a formatted date, the method
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

        assert input != null : "Date input for task is null";
        String[] strComponents = input.strip().split(" ");

        if (strComponents.length > 2) {
            return null;
        } else if (strComponents.length == 2) {
            LocalDateTime dateTime = DateParser.parseDateTime(input.strip());
            return new DukeDate(dateTime);
        } else if (strComponents.length == 1) {
            LocalDate date = DateParser.parseDate(input.strip());
            return new DukeDate(date);
        } else {
            throw new DukeInvalidDateException();
        }
    }

    /**
     * Returns a duke.DukeDate that has been previously saved.
     * Duke saves newly created or edited tasks into local storage. A DukeDate is converted to a string
     * and then stored in the text file. The String describes the date itself along with its type.
     * When reloading the tasks from the text file, this method takes the date String and reconverts
     * it back to a DukeDate.
     *
     * @param dateString The String that the DukeDate was saved as.
     * @param type The type of the saved DukeDate.
     * @return The reconverted DukeDates.
     * @throws exceptions.DukeInvalidStorageTaskException When unable to read the date from storage.
     */
    public static DukeDate getDukeDateFromStorage(String dateString, int type)
            throws DukeInvalidStorageTaskException {
        if (type == DukeDate.HAS_STRING) {
            return new DukeDate(dateString);
        } else if (type == DukeDate.HAS_DATE) {
            return new DukeDate(LocalDate.parse(dateString, DateParser.PRINT_DATE_FORMATTER));
        } else if (type == DukeDate.HAS_DATE_TIME) {
            return new DukeDate(LocalDateTime.parse(dateString, DateParser.PRINT_DATE_TIME_FORMATTER));
        } else {
            throw new DukeInvalidStorageTaskException();
        }
    }

    /**
     * Returns the type of date the DukeDate is storing as a String.
     *
     * @return The required String.
     */
    public String getDateType() {
        return Integer.toString(this.dateType);
    }

    @Override
    public String toString() {
        if (this.dateTime != null) {
            return this.dateTime.format(DateParser.PRINT_DATE_TIME_FORMATTER);
        } else if (this.date != null) {
            return this.date.format(DateParser.PRINT_DATE_FORMATTER);
        } else {
            assert dateString != null : "Encountered an error while reading a task date";
            return dateString;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DukeDate dukeDate = (DukeDate) o;
        return dateType == dukeDate.dateType && Objects.equals(dateString, dukeDate.dateString)
                && Objects.equals(date, dukeDate.date) && Objects.equals(dateTime, dukeDate.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateString, date, dateTime, dateType);
    }
}
