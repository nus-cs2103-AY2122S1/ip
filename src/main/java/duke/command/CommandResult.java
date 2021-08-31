package duke.command;

public class CommandResult {
    String feedbackToUser;
    boolean isReply;
    boolean isExit;

    public CommandResult(String feedbackToUser, boolean isReply, boolean isExit) {
        this.feedbackToUser = feedbackToUser;
        this.isReply = isReply;
        this.isExit = isExit;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isReply() {
        return isReply;
    }

    public boolean isExit() {
        return isExit;
    }
}
