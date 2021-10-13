package bobbybot.exceptions;

/**
 * Exception for invalid save file
 */
public class InvalidSaveFileException extends BobbyException {
    public InvalidSaveFileException(String message) {
        super(message);
    }
}
