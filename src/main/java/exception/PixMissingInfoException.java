package exception;

/**
 * Triggers when there is missing information in the command.
 */
public class PixMissingInfoException extends PixException {
    private String command;

    /**
     * Constructor of the PixMissingInfoException.
     */
    public PixMissingInfoException(String command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        switch (command) {
        case "todo":
            return "The information of the todo task is incomplete!";
        case "deadline":
            return "The information of the deadline is incomplete!";
        case "event":
            return "The information of the event is incomplete!";
        case "done":
            return "Which task did you do?";
        case "list":
            return "You need to input a task number!";
        case "delete":
            return "The task cannot be found. Which task do you want deleted?";
        default:
            return "There is insufficient information!";
        }
    }
}