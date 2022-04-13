package poseidon.exception;

/**
 * Represents a {@code PoseidonStorageReadWriteException} for exceptions during read and write operations that occur
 * in {@code Storage}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonStorageReadWriteException extends PoseidonException {

    /**
     * Constructs a new {@code PoseidonStorageReadWriteException} with the given message and some additional info
     * about the nature of the exception.
     *
     * @param exceptionMsg {@code String} containing the information about the new exception.
     */
    public PoseidonStorageReadWriteException(String exceptionMsg) {
        super("Read and/or Write operations with the file on the local hard disk failed.\n"
                + exceptionMsg + "\n"
                + "Please exit the bot and fix the issue to prevent any data loss.");
    }
}
