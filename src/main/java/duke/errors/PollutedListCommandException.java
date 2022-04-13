package duke.errors;

public class PollutedListCommandException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the PollutedListCommandException.
     */
    public PollutedListCommandException() {
        super(1);
        this.errorDescription = "Please only type 'list' to view the task list.";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
