package utils.exceptions;

public class DukeException extends Exception {
    public static final String NOT_LOADED_WARNING = "Warning: Failed to load from hard disk.";

    public DukeException(String message) {
        super(message);
    }

}
