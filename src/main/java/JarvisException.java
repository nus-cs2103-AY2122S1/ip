public class JarvisException extends Exception {

    protected String message;

    public JarvisException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
