public class WrongCommandFormatException extends Exception{

    private String message;

    public WrongCommandFormatException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
