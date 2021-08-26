public class DeadlineException extends DukeException1 {
    DeadlineException() {
        super();
    }

    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a deadline cannot be empty.";
    }
}
