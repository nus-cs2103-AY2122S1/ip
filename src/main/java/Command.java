/**
 * All commands that Duke can handle
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public enum Command {
    LIST,
    DONE,
    DEADLINE,
    TODO,
    EVENT,
    DELETE,
    BYE,
    INVALID;

    /**
     * Looks up an enum value from user input
     *
     *
     * @param input command that user input.
     * @return a command that corresponds to user input. Returns INVALID if input does not match any commands.
     */
    public static Command evaluateInput(String input) {
        for (Command cmd : values()) {
            if (input.equalsIgnoreCase(cmd.toString())) {
                return cmd;
            }
        }
        return INVALID;
    }
}
