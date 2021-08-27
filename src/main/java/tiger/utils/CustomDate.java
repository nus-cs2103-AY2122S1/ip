package tiger.utils;


import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The {@code CustomDate} class represents possible input dates that the user
 * wishes to key in. A date, {@code localDate}, is necessary, however a
 *
 * {@code localTime} may not be necessary, in the case for all day tasks.
 * This is indicated by a boolean variable {@code hasTime}.
 */

public class CustomDate {

    private final LocalDate localDate;
    private final LocalTime localTime;
    boolean hasTime;

    /**
     * Constructor for the {@code CustomDate} class.
     *
     * @code param localDate Date to be added.
     * @code param localTime Time to be added.
     * @code param hasTime Whether the user wants to have a specified time.
     */

    public CustomDate(LocalDate localDate, LocalTime localTime, boolean hasTime) {
        this.localDate = localDate;
        this.localTime = localTime;
        this.hasTime = hasTime;
    }

    /**
     * Returns a string representation of {@code CustomDate}.
     * @return the string representation of {@code CustomDate}.
     */

    @Override
    public String toString() {
        if (hasTime) {
            return String.format("%s %s", this.localDate.toString(), this.localTime.toString());
        } else {
            return String.format("%s", this.localDate.toString());
        }
    }
}
