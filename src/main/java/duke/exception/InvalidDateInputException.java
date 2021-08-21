package duke.exception;

/**
 * The DukeException is an exception that is thrown when Duke is run.
 *
 * It contains the exceptions that are thrown due to wrong inputs given and is specific to this version of Duke.
 * Names of exception should be self-explanatory in describing when it should be used.
 *
 * @author Benedict Chua
 */
public class InvalidDateInputException extends DukeException {
    public InvalidDateInputException(String dateString) {
        super("BAKA! I don't understand this Date input!\n" +
                String.format("     Date: %s\n", dateString) +
                "     It should be a valid date in the form dd-mm-yyyy, dd/mm/yyyy or yyyy-mm-dd!");
    }
}
