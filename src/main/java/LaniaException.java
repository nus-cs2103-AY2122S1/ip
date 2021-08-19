public class LaniaException extends IllegalArgumentException {

    LaniaException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Oh no!! " + getMessage();
    }
}
