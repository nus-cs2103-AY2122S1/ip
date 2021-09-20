package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents dates used by the Deadline and Event classes. Internally contains a
 * java.time.LocalDate object that outputs the date in a standardised pattern,
 * but silently falls back to using a string if the input cannot be parsed.
 */
public class DateString implements Comparable<DateString>{
    private final String dateString;
    private LocalDate localDate;

    /**
     * Constructor which takes in a string and attempts to parse it to create a LocalDate
     * object. If the parsing is unsuccessful, silently defaults to using a string instead.
     * @param str
     */
    public DateString(String str) {
        this.dateString = str;
        try {
            this.localDate = LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            this.localDate = null;
        }
    }

    /**
     * Returns the toString() method of the internal LocalDate object if valid,
     * or else returns the string given to the constructor.
     *
     * @return The string representation of this object.
     */

    public String toString() {
        if (this.localDate != null) {
            return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.dateString;
        }
    }

    @Override
    public int compareTo(DateString other) {
        if ((this.localDate == null) && (other.localDate == null)) {
            return 0;
        } else if (this.localDate == null) {
            return -1;
        } else if (other.localDate == null) {
            return 1;
        } else {
            return this.localDate.compareTo(other.localDate);
        }
    }

}
