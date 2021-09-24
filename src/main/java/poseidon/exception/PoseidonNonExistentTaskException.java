package poseidon.exception;

/**
 * Represents a {@code PoseidonNonExistentTaskException} for non-existent {@code Task} access errors.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonNonExistentTaskException extends PoseidonException {

    /**
     * Constructs a new {@code PoseidonNonExistentTaskException} with info about the nature of the error.
     */
    public PoseidonNonExistentTaskException() {
        super("That task doesn't exist.\n"
                + "Please Try again.");
    }

}
