package duke.command;

/**
 * CommandResult class abstracts behavior of the command after completing
 */
public class CommandResult {
    private final String feedbackToUser;
    private final boolean isReply;
    private final boolean isExit;

    /**
     * Constructor of the CommandResult class
     *
     * @param feedbackToUser text to display to the user
     * @param isReply whether the text is a reply from duke
     * @param isExit whether to exit the application
     */
    public CommandResult(String feedbackToUser, boolean isReply, boolean isExit) {
        this.feedbackToUser = feedbackToUser;
        this.isReply = isReply;
        this.isExit = isExit;
    }

    /**
     * Returns text to display to user
     *
     * @return text to display to user
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Getter of isReply
     *
     * @return isReply
     */
    public boolean isReply() {
        return isReply;
    }

    /**
     * Getter of isExit
     *
     * @return isExit
     */
    public boolean isExit() {
        return isExit;
    }
}
