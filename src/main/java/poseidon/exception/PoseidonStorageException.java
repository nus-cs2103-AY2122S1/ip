package poseidon.exception;

/**
 * Represents a {@code PoseidonStorageException} for exceptions during file access operations that occur in
 * {@code Storage}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonStorageException extends PoseidonException {

    /**
     * Constructs a new {@code PoseidonStorageException} with the given message and some additional info
     * about the nature of the exception.
     *
     * @param exceptionMsg {@code String} containing information about the new exception.
     */
    public PoseidonStorageException(String exceptionMsg) {
        super(exceptionMsg + "\n"
                + "Please exit the bot and fix the issue to prevent any data loss.");
    }
}
