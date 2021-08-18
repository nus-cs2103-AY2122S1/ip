public class IncompleteDeadlineException extends DukeException{
    private static String line = "____________________________________________________________";
    private static String message = "OOPS!!! The description or date of a deadline cannot be empty.";
    public IncompleteDeadlineException() {
        super(line + "\n" + message + "\n" + line);
    }
}
