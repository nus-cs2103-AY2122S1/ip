package duke.exceptions;

public class EventFormatException extends DukeException {
    public EventFormatException() {
        super("☹ OOPS!!! Please use the format: event <description> /from yyyy-mm-ddTHH:mm /to yyyy-mm-ddTHH:mm");
    }
}