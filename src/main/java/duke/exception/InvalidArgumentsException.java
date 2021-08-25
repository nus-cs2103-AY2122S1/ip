package duke.exception;

public class InvalidArgumentsException extends DukeException{
    public InvalidArgumentsException() {
        super("Invalid or Missing Arguments");
    }
}