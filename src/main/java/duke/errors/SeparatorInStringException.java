package duke.errors;

public class SeparatorInStringException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the SeparatorInStringException.
     */
    public SeparatorInStringException () {
        super(11);
        this.errorDescription = "Please do not use '_~_' in your input as it breaks me! ):";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
