package duke;

/**
 * An exception class for us to handle all the different exceptions in the application
 * while allowing the application to handle their respective error messages
 */
public class DukeException extends Exception {
    private final String errorMessage;

    /**
     * Class constructor specifying error type
     *
     * @param errorType The type of error to result in the correct error message
     */
    public DukeException(String errorType) {
        assert(errorType != null);
        switch (errorType) {
        case "invalid input":
            errorMessage = "Invalid input, try using todo, deadline or event";
            break;
        case "empty todo":
            errorMessage = "Description of ToDo cannot be empty dear </3";
            break;
        case "empty deadline":
            errorMessage = "Description of Deadline cannot be empty dear </3";
            break;
        case "empty event":
            errorMessage = "Description of Event cannot be empty dear </3";
            break;
        case "deadline format":
            errorMessage = "Invalid deadline formatting!\n"
                    + "Proper format: deadline 'deadline description' /by 'deadline'";
            break;
        case "event format":
            errorMessage = "Invalid event formatting!\n"
                    + "Proper format: event 'event description' /at 'location'";
            break;
        case "do after format":
            errorMessage = "Invalid do after formatting!\n"
                    + "Proper format: doafter 'doafter description' /after 'previous task'";
            break;
        case "initialization error":
            errorMessage = "data.txt is corrupted!\n"
                    + "Please delete data.txt to start fresh";
            break;
        case "date parse":
            errorMessage = "Cannot parse date format!\n"
                    + "Proper format: dd/MM/yyyy HHmm";
            break;
        case "file not found":
            errorMessage = "Saved file not found!\n"
                    + "Creating empty list...";
            break;
        case "invalid task":
            errorMessage = "This task does not exist!\n"
                    + "Please enter a valid task to be deleted.";
            break;
        default:
            errorMessage = "unknown error message";
        }
    }

    /**
     * A method to obtain the error message from the DukeException.
     *
     * @return The requires error message.
     */
    @Override
    public String getMessage() {
        return errorMessage;
    }
}
