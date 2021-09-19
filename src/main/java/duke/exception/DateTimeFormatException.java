package duke.exception;

/**
 * Throws exception when date and time are not in parsable format.
 */
public class DateTimeFormatException extends DukeException {

    /**
     * Throws exception when cannot parse date and time.
     *
     * @param datetime Date time string
     */
    public DateTimeFormatException(String datetime) {
        super(("Meow? You entered " + datetime
                + ".\n   Please input date in 31/12/2021 or 2021-12-31 or 31Dec2021 format."
                + "\n   Please input time in 2300 or 11pm or 11.00pm format."));
    }
}
