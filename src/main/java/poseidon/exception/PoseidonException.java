package poseidon.exception;

/**
 * Represents a custom {@code Exception} for {@code Poseidon}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonException extends Exception {

    /**
     * Constructs a new {@code PoseidonException} with the given error message.
     *
     * @param errorMsg Error message of the new {@code Exception}.
     */
    public PoseidonException(String errorMsg) {
        super(errorMsg);
    }

}
