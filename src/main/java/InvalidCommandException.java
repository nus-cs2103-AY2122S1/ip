public class InvalidCommandException extends DukeException {
    private static String line = "____________________________________________________________";
    private static String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public InvalidCommandException() {
            super(line + "\n" + message + "\n" + line);
    }
}
