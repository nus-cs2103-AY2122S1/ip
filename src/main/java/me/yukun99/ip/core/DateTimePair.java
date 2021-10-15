package me.yukun99.ip.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;

/**
 * Class that stores both a date and time field.
 */
public class DateTimePair {
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for a DateTimePair instance.
     *
     * @param date Date stored in this instance.
     * @param time Time stored in this instance.
     */
    public DateTimePair(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Updates the current DateTimePair instance with newly parsed values.
     *
     * @param pair DateTimePair instance containing newly parsed values.
     */
    public void update(DateTimePair pair) {
        date = pair.date;
        time = pair.time;
    }

    /**
     * Parses a String to obtain a DateTimePair object.
     *
     * @param strDate String to be parsed.
     * @return DateTimePair instance with the date and time specified in the String.
     * @throws HelpBotDateTimeFormatException If date or time is provided in the wrong format.
     */
    public static DateTimePair parse(String strDate) throws HelpBotDateTimeFormatException {
        String[] dateSplit = strDate.split(" ");
        LocalDate date;
        LocalTime time = null;
        try {
            date = LocalDate.parse(dateSplit[0]);
        } catch (DateTimeParseException e) {
            throw new HelpBotDateTimeFormatException(dateSplit[0]);
        }
        try {
            time = LocalTime.parse(dateSplit[1]);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException ignored) {
            //ignored
        }
        return new DateTimePair(date, time);
    }

    /**
     * Checks if another DateTimePair instance has the same date.
     *
     * @param o DateTimePair instance to be checked against.
     * @return Whether the other DateTimePair instance has the same date.
     */
    public boolean hasEqualDate(DateTimePair o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        return date.equals(o.date);
    }

    /**
     * Gets the task saving formatting of the DateTimePair.
     *
     * @return Task saving formatting of the DateTimePair.
     */
    public String saveString() {
        if (time == null) {
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " "
                + time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DateTimePair)) {
            return false;
        }
        DateTimePair dateTimePair = (DateTimePair) o;
        return date.equals(dateTimePair.date) && time.equals(dateTimePair.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time);
    }

    @Override
    public String toString() {
        if (time == null) {
            return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        }
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ", "
                + time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    }
}
