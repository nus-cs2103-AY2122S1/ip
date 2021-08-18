public class MissingDeadlineDescriptionException extends Exception {
    public MissingDeadlineDescriptionException () {
        super("☹ OOPS!!! The description of a deadline cannot be empty.");
    }
}