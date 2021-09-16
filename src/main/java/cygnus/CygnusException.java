package cygnus;

/**
 * Represents a CygnusException, which is a subtype of Exception.
 * Encapsulates numerous errors which could occur during runtime
 * due to invalid user input or other reasons.
 *
 * @author Joshua Yong
 */
class CygnusException extends Exception {

    /**
     * Class constructor.
     *
     * @param message The error message.
     */
    public CygnusException(String message) {
        super(message);
    }

}
