package duke.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

/** A Date wrapper class. */
public class Date {
    /** The LocalDate. */
    private LocalDate date;

    /**
     * The Date constructor.
     *
     * @param date The LocalDate.
     */
    Date(LocalDate date) {
        this.date = date;
    }

    /**
     * Date factory method.
     *
     * @param dateString The date string in yyyy-MM-dd format.
     * @return A Date object.
     * @throws InvalidDateException If the given dateString is not in yyyy-MM-dd format.
     */
    public static Date of(String dateString) throws InvalidDateException {
        try {
            return new Date(LocalDate.parse(dateString));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Parses the Date into a JSON string.
     *
     * @return A JSON string representation of the Date.
     */
    public String toJsonString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Date) {
            Date another = (Date) other;
            return another.toString().equals(this.toString());
        }
        return false;
    }
}
