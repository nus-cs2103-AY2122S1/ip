public class MissingEventDescriptionException extends Exception {
    public MissingEventDescriptionException () {
        super("☹ OOPS!!! The description of an event cannot be empty.");
    }
}