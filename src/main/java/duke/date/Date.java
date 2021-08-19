package duke.date;

import duke.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    private LocalDate date;

    private Date(LocalDate date) {
        this.date = date;
    }

    public static Date of(String dateString) throws InvalidDateException {
        try {
            return new Date(LocalDate.parse(dateString));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
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
