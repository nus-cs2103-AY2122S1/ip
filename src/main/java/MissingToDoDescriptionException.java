public class MissingToDoDescriptionException extends Exception {
    public MissingToDoDescriptionException () {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}