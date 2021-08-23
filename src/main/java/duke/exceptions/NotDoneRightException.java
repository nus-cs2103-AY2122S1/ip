package duke.exceptions;

public class NotDoneRightException extends DukeExceptions {
    public NotDoneRightException(String start, String end) {
        super("Please input an integer in the range of " + start + " to " + end + "!");
    }
}
