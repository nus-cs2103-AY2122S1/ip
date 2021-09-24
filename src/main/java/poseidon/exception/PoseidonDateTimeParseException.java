package poseidon.exception;

/**
 * Represents a {@code PoseidonDateTimeParseException} for Date and Time parsing errors.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonDateTimeParseException extends PoseidonException {

    /**
     * Constructs a new {@code PoseidonDateTimeParseException} with the given message and some additional info
     * about the nature of the error.
     *
     * @param errorMsg {@code String} containing the error message.
     */
    public PoseidonDateTimeParseException(String errorMsg) {
        super("Date and Time couldn't be parsed.\n"
                + errorMsg + "\n"
                + "Please try again.");
    }
}
