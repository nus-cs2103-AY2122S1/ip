package duke.exception;

/**
 * Exception of Duke that occurs when an incorrect format is given for the Expense command.
 */
public class InvalidExpenseFormatException extends DukeException {

    /**
     * Constructor of InvalidExpenseFormatException.
     */
    public InvalidExpenseFormatException() {
        super("You entered a wrong format for Expense! Please use the formats:\n"
                + "expense (index) (purpose) $(amount) or\n"
                + "expense (index) or\n"
                + "expense (/listall) or\n"
                + "expense (index) (/delete) (expenseIndex) or\n"
                + "expense (index) (/sum) or\n"
                + "expense (/sumall).");
    }
}
