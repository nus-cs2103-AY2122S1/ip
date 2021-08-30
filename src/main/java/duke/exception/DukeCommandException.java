package duke.exception;

/**
 * Represents Duke command related exceptions.
 * Generally used to handle when user uses the command wrongly.
 */
public class DukeCommandException extends Exception {
    private String msg;

    /**
     * Creates a new DukeCommandException
     *
     * @param command Command used by the user.
     */
    public DukeCommandException(String command) {
        switch (command) {
        case "todo":
            msg = "Wrong usage of \"todo\".\nCorrect usage: \"todo TASK_NAME\".";
            break;
        case "deadline":
            msg = "Wrong usage of \"deadline\".\nCorrect usage: \"deadline TASK_NAME /by TIME\".";
            break;
        case "event":
            msg = "Wrong usage of \"event\".\nCorrect usage: \"event TASK_NAME /at TIME\".";
            break;
        case "done":
            msg = "Wrong usage of \"done\".\nCorrect usage: \"done TASK_NUMBER\".";
            break;
        case "delete":
            msg = "Wrong usage of \"delete\".\nCorrect usage: \"delete TASK_NUMBER\"";
            break;
        case "find":
            msg = "Wrong usage of \"find\".\nCorrect usage: \"find KEYWORD\"";
            break;
        default:
            msg = command + " is not a supported command.";
        }
    }

    /**
     * Returns the error message
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return msg;
    }
}
