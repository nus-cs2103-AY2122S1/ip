package Exception;

public class DukeException extends Exception {
    public DukeException(String error) {
        super("Invalid command! " + error);
    }
}