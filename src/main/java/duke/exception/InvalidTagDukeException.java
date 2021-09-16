package duke.exception;

public class InvalidTagDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! Tag parameter is missing.\n"
            + "eg. tag 2 Important";

    public InvalidTagDukeException() {
        super(ERROR_MESSAGE);
    }
}
