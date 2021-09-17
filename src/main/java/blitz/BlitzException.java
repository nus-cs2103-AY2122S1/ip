package blitz;

public class BlitzException extends Exception {

    protected String message;

    public BlitzException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.message;
    }
}
