package tiger.exceptions;

public class TigerException extends RuntimeException {

    String error;

    public TigerException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error;
    }

}
