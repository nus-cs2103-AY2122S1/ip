package duke.commandresult;

/**
 * A class which is instantiated whenever a command is executed.
 * This class stores the feedback result string as well as a boolean value which
 * tells Duke to quit and complete running.
 */
public class CommandResult {

    private final String feedbackResult;
    private final boolean isExit;

    /**
     * A constructor produces a CommandResult
     * @param feedbackResult The string to be returned to the User Interface to be rendered.
     * @param isExit The boolean value signifying Duke to exit.
     */
    public CommandResult(String feedbackResult, boolean isExit) {
        this.feedbackResult = feedbackResult;
        this.isExit = isExit;
    }

    /**
     * A getter to retrieve the feedbackResult
     * @return feedbackResult
     */
    public String getFeedbackResult() {
        return this.feedbackResult;
    }

    /**
     * A getter to retrieve the isExit boolean value.
     * @return isExit
     */
    public boolean isExit() {
        return this.isExit;
    }
}
