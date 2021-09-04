package duke;

/**
 * Represents errors specific to Duke mostly to handle wrong user input.
 */
public class DukeException extends IllegalArgumentException {
    private String errorString = "";

    /**
     * Constructor for DukeException.
     *
     * @param errorType String to specify which error is thrown.
     */
    public DukeException(String errorType) {
        switch (errorType) {
        case "invalid input":
            errorString = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        case "empty command":
            errorString = "OOPS!!! Please enter a command.";
            break;
        case "invalid task index":
            errorString = "OOPS!!! Please enter a valid task index.";
            break;
        case "non-integer input":
            errorString = "OOPS!!! Please enter an integer.";
            break;
        case "empty search term":
            errorString = "OOPS!!! Please enter the term you want to search.";
            break;
        case "empty todo description":
            errorString = "OOPS!!! The description of a todo cannot be empty.";
            break;
        case "empty deadline description":
            errorString = "OOPS!!! The description of a deadline cannot be empty.";
            break;
        case "empty deadline deadline":
            errorString = "OOPS!!! The deadline of a... deadline cannot be empty.";
            break;
        case "invalid deadline":
            errorString = "OOPS!!! Please enter a valid deadline!";
            break;
        case "empty event description":
            errorString = "OOPS!!! The description of an event cannot be empty.";
            break;
        case "empty event duration":
            errorString = "OOPS!!! The duration of an event cannot be empty.";
            break;
        case "invalid event date":
            errorString = "OOPS!!! Please enter a valid date in duration!";
            break;
        case "invalid event time":
            errorString = "OOPS!!! Please enter a valid time duration!"
                + " Valid formats are (HHmm-HHmm or hh:mm a-hh:mm a)";
            break;
        case "empty recurrence number":
            errorString = "OOPS!!! Please enter a valid number of recurrence.";
            break;
        default:
            assert false : "Unaccounted exception case";
        }
    }
    @Override
    public String getMessage() {
        return errorString;
    }
}
