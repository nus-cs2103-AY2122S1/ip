package skeltal;

/**
 * A class to handle exceptions for the Skeltal program.
 */
public class SkeltalException extends Exception {
    /**
     * A constructor to initialise a SkeltalException object.
     * @param errorMessage A String representative of the error message that will be printed.
     */
    public SkeltalException(String errorMessage) {
        super(errorMessage);
    }
}
