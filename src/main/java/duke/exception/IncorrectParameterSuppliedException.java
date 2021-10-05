package duke.exception;

/**
 * An exception for any incorrect parameters supplied.
 */
public class IncorrectParameterSuppliedException extends DukeException {

    /** A class level constant to represent the prefix of the message.*/
    public static final String INCORRECT_PARAMETER_PREFIX = "Incorrect parameters were supplied for ";

    /**
     * The constructor method for DukeException.
     *
     * @param type The type of command.
     * @param specificFormat The list of formats needed for parameters specified.
     */
    public IncorrectParameterSuppliedException(String type, String[] specificFormat) {
        super(combinePrefixAndParameters(type, specificFormat));
    }

    /**
     * Returns a message which can be used as the message for the IncorrectParameterSuppliedException
     * @param type The String indicating the type of command
     * @param specificFormat The list of parameter formats to be included in the message.
     * @return a message which can be used as the message for the IncorrectParameterSuppliedException
     */
    public static String combinePrefixAndParameters(String type, String[] specificFormat) {
        StringBuilder result = new StringBuilder(INCORRECT_PARAMETER_PREFIX + type + ".\nTry this: ");
        for (String s : specificFormat) {
            result.append(" ").append(s).append(" ");
        }
        return result.toString();
    }
}
