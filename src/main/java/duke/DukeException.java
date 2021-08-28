package duke;

public class DukeException extends Exception {

    /** String with details on the exception */
    private String errorMessage;
    /** Types of exceptions */
    public enum Type {
        DESCRIPTION, INDEX, COMMAND, DEADLINE, EVENT
    }

    /**
     * Returns a new DukeException object.
     *
     * @param s Type of exception it is initialized to.
     */
    public DukeException(Type s) {
        switch (s) {
        case DESCRIPTION:
            this.errorMessage = "The description of a task cannot be empty";
            break;
        case INDEX:
            this.errorMessage = "The index of the task is out of range";
            break;
        case COMMAND:
            this.errorMessage = "I don't know what that means\n"
                    + "Type help to get a list of available commands!";
            break;
        case DEADLINE:
            this.errorMessage = "Usage of deadline does not match 'description' /by 'deadline'";
            break;
        case EVENT:
            this.errorMessage = "Usage of event does not match 'description' /at 'timeframe'";
            break;
        default:
            break;
        }
    }

    /**
     * Returns a the error message.
     *
     * @return Error message in the exception.
     */
    @Override
    public String toString() {
        return errorMessage;
    }
}
