package duke.exceptions;

public class WrongEventOrDeadlineFormatException extends DukeException {
    public WrongEventOrDeadlineFormatException() {
        super("Event or Deadline task requires a date and time input!");
    }
}
