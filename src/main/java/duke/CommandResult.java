package duke;

public class CommandResult {
    private final String feedbackResult;
    private final boolean isExit;

    public CommandResult(String feedbackResult, boolean isExit) {
        this.feedbackResult = feedbackResult;
        this.isExit = isExit;
    }

    public String getFeedbackResult() {
        return this.feedbackResult;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
