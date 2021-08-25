package duke.exception;

public class DukeUnexpectedCharacterException extends DukeException {
    public DukeUnexpectedCharacterException(String symbol) {
        super(String.format("Unexpected symbol: %s ", symbol));
    }
}