package duke.exception;

public class IncorrectParameterSuppliedException extends DukeException {

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

    public static String combinePrefixAndParameters(String type, String[] specificFormat) {
        StringBuilder result = new StringBuilder(INCORRECT_PARAMETER_PREFIX + type + ".\nTry this: ");
        for (String s : specificFormat) {
            result.append(" ").append(s).append(" ");
        }
        return result.toString();
    }
}
