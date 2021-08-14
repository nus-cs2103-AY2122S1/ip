public class EmptyDescException extends PetalException {

    private String message;

    public EmptyDescException(String message) {
        super(message);
        this.message = message;
    }
}
