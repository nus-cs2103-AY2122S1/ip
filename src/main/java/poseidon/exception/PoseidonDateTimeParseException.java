package poseidon.exception;

/**
 * Represents a {@code PoseidonDateTimeParseException} for Date and Time parsing exceptions.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonDateTimeParseException extends PoseidonException {

    /**
     * Constructs a new {@code PoseidonDateTimeParseException} with the given message and some additional info
     * about the nature of the exception.
     *
     * @param exceptionMsg {@code String} containing the information about the new exception.
     */
    public PoseidonDateTimeParseException(String exceptionMsg) {
        super("Date and Time couldn't be parsed.\n"
                + exceptionMsg + "\n"
                + "Please try again.");
    }
}
