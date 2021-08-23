package duke.exceptions;

public class DeletionException extends DukeExceptions {
    public DeletionException(String start, String end) {
        super("Please input an integer in the range of " + start + " to " + end + "!");
    }
}
