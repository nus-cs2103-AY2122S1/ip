public class IncompleteToDoException extends DukeException{
    private static String line = "____________________________________________________________";
    private static String message = "OOPS!!! The description of a todo cannot be empty.";
    public IncompleteToDoException() {
        super(line + "\n" + message + "\n" + line);
    }
}
