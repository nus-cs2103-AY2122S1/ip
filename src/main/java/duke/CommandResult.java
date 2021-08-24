package duke;

/**
 * The class which stores the feedback result and the isExit to be returned to the UserInterface.
 */
public class CommandResult {

    private final String feedbackResult;
    private final boolean isExit;

    /**
     * Constructor for the CommandResult Task.
     * @param feedbackResult The string to be returned to UserInterface.
     * @param isExit The exit boolean to be returned to UserInterface.
     */
    public CommandResult(String feedbackResult, boolean isExit) {
        this.feedbackResult = feedbackResult;
        this.isExit = isExit;
    }

    /**
     * Getter that retrieves the feedback result.
     * @return String
     */
    public String getFeedbackResult() {
        return this.feedbackResult;
    }

    /**
     * Getter that retrieves the isExit boolean.
     * @return boolean
     */
    public boolean isExit() { return this.isExit; }
}
