package duke.exception;

public class InvalidDeadlineDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "\"OOPS!!! Parameter /by is missing.\n"
            + "eg. deadline Read Book /by 31/12/2021 1800\n"
            + "    deadline Read Book /by Friday\n";

    public InvalidDeadlineDukeException() {
        super(ERROR_MESSAGE);
    }
}
