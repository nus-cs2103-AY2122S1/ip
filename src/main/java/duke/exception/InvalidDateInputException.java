package duke.exception;

/**
 * The InvalidDateInputException is thrown when the user gives a date that is not valid or not in an acceptable form.
 *
 * @author Benedict Chua
 */
public class InvalidDateInputException extends DukeException {
    /**
     * Constructor for InvalidDateInputException.
     *
     * @param dateString String containing the date inputted in an incorrect format by the user.
     */
    public InvalidDateInputException(String dateString) {
        super("BAKA! I don't understand this Date input!\n"
                + String.format("Date: %s\n", dateString)
                + "It should be a valid date in the form dd-mm-yyyy, dd/mm/yyyy or yyyy-mm-dd!");
    }
}
