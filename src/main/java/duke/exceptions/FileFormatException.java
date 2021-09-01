package duke.exceptions;

public class FileFormatException extends Exception{
    public FileFormatException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
