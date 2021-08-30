package dino.exception;

public class InvalidFormatException extends DinoException {
    public InvalidFormatException(String action, String format) {
        super("Please " + action + " is entered in the format: " + format);
    }
}
