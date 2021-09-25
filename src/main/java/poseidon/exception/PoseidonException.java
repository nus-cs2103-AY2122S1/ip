package poseidon.exception;

/**
 * Represents a custom {@code Exception} for {@code Poseidon}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonException extends Exception {

    /**
     * Constructs a new {@code PoseidonException} with the given message.
     *
     * @param exceptionMsg {@code String} containing the information about the new exception.
     */
    public PoseidonException(String exceptionMsg) {
        super(exceptionMsg);
    }

}
