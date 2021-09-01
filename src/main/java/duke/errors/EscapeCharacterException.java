package duke.errors;

public class EscapeCharacterException extends DukeException {

    private final String errorDescription;

    /**
     * The constructor for the EscapeCharacterException.
     */
    public EscapeCharacterException () {
        super(0);
        this.errorDescription = "Please do not use the \\n in your input as it makes me sad. ):";
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

}
