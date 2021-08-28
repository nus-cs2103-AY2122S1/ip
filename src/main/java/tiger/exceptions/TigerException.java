package tiger.exceptions;

public class TigerException extends RuntimeException {

    private String error;

    public TigerException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error;
    }

}
