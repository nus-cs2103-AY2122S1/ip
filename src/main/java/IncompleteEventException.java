public class IncompleteEventException extends DukeException{
    private static String line = "____________________________________________________________";
    private static String message = "☹ OOPS!!! The description or date of an event cannot be empty.";
    public IncompleteEventException() {
        super(line + "\n" + message + "\n" + line);
    }
}
