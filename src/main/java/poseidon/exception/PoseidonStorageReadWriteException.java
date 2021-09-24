package poseidon.exception;

/**
 * Represents a {@code PoseidonStorageReadWriteException} for errors in read and write operations that occur
 * in {@code Storage}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonStorageReadWriteException extends PoseidonException {

    /**
     * Constructs a new {@code PoseidonStorageReadWriteException} with the given message and some additional info
     * about the nature of the error.
     *
     * @param errorMsg {@code String} containing the error message.
     */
    public PoseidonStorageReadWriteException(String errorMsg) {
        super("Read and/or Write operations with the file on the local hard disk failed.\n"
                + errorMsg + "\n"
                + "Please exit the bot and fix the issue to prevent any data loss.");
    }
}
