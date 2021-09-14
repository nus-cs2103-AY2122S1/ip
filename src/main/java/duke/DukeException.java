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
        //@@author muhammad-faruq-reused
        //Reused from https://github.com/muhammad-faruq/ip/blob/master/src/main/java/duke/DukeException.java
        // with modifications to suit current exception cases
        switch (errorType) {
        case "invalid input":
            errorString = "I don't understand, come again?";
            break;
        case "empty command":
            errorString = "Enter the command.";
            break;
        case "empty task list":
            errorString = "You have no tasks to begin with...";
            break;
        case "invalid task index":
            errorString = "Can you actually PLEASE enter a valid task index???";
            break;
        case "non-integer input":
            errorString = "Do you know what an integer input is?";
            break;
        case "empty search term":
            errorString = "I cannot search for nothing, enter something!";
            break;
        case "empty todo description":
            errorString = "I'm not going to add a todo of nothing.";
            break;
        case "empty deadline description":
            errorString = "I'm not going to add a deadline of nothing.";
            break;
        case "empty deadline deadline":
            errorString = "Well deadline of a... deadline cannot be empty.";
            break;
        case "invalid deadline":
            errorString = "Use a sensible deadline date/time format..";
            break;
        case "empty event description":
            errorString = "I'm not going to add an event of nothing.";
            break;
        case "empty event duration":
            errorString = "Tell me how long the event lasts.";
            break;
        case "invalid event date":
            errorString = "Use a sensible event date format..";
            break;
        case "invalid event time":
            errorString = "Use a sensible event time format..";
            break;
        case "empty recurrence number":
            errorString = "Can you give me a sensible number of repetitions???";
            break;
        default:
            assert false : "Unaccounted exception case";
        }
    }
    @Override
    public String getMessage() {
        return "ERROR: " + errorString;
    }
}
