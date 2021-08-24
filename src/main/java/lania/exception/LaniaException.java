package lania.exception;

public class LaniaException extends IllegalArgumentException {

    public LaniaException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Oh no!! " + getMessage();
    }
}
