package chadbot;

public class ChadException extends Exception {

    /** String with details on the exception */
    private String errorMessage;
    /** Types of exceptions */
    public enum Type {
        DESCRIPTION, INDEX, COMMAND, DEADLINE, EVENT, EXECUTE
    }

    /**
     * Returns a new ChadException object.
     *
     * @param s Type of exception it is initialized to.
     */
    public ChadException(Type s) {
        switch (s) {
        case DESCRIPTION:
            this.errorMessage = "The description of a task cannot be empty.";
            break;
        case INDEX:
            this.errorMessage = "The index of the task is out of range.";
            break;
        case COMMAND:
            this.errorMessage = "I don't know what that means.\n"
                    + "Type help to get a list of available commands.";
            break;
        case DEADLINE:
            this.errorMessage = "Usage of deadline does not match 'description' /by 'deadline'";
            break;
        case EVENT:
            this.errorMessage = "Usage of event does not match 'description' /at 'timeframe'";
            break;
        case EXECUTE:
            this.errorMessage = "There is a problem executing this command.";
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
