package poseidon.exception;

/**
 * Represents a {@code PoseidonStorageException} for errors in file access operations that occur in {@code Storage}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonStorageException extends PoseidonException {

    /**
     * Constructs a new {@code PoseidonStorageException} with the given message and some additional info
     * about the nature of the error.
     *
     * @param errorMsg {@code String} containing the error message.
     */
    public PoseidonStorageException(String errorMsg) {
        super(errorMsg + "\n"
                + "Please exit the bot and fix the issue to prevent any data loss.");
    }
}
