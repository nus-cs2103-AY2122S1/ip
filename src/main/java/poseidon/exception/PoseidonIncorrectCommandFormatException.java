package poseidon.exception;

/**
 * Represents a {@code PoseidonIncorrectCommandFormatException} for {@code Command} format exceptions.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonIncorrectCommandFormatException extends PoseidonException {

    /**
     * Constructs a new {@code PoseidonIncorrectCommandFormatException} with the given message and some additional info
     * about the nature of the exception.
     *
     * @param commandName {@code String} containing the name of the {@code Command}.
     * @param correctFormat {@code String} containing the correct format of the {@code Command}.
     */
    public PoseidonIncorrectCommandFormatException(String commandName, String correctFormat) {
        super("There appears to be a typo in your " + commandName + " command.\n"
                + "The command should be of the form:\n"
                + "  " + correctFormat + "\n"
                + "Please try again.");
    }

}
