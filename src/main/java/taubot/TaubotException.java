package taubot;

/**
 * Represents exceptions that Taubot will throw when the user
 * uses the wrong commands on Taubot.
 */
public class TaubotException extends RuntimeException {
    public TaubotException(String errorMessage) {
        super(errorMessage);
    }
}
