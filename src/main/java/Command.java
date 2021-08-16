/**
 * Commands that Duke can handle.
 *
 * @author Chng Zi Hao
 */
public enum Command {
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    HELP("help"),
    BYE("bye"),
    DELETE("delete"),
    INVALID("invalid");

    public final String label;

    private Command(String label) {
        this.label = label;
    }

    /**
     * Look up an enum value by our label field.
     *
     * @param label command that user input.
     * @return a Command that corresponds to user input. Returns INVALID if input does not match any Commands.
     */
    public static Command valueOfLabel(String label) {
        for (Command c : values()) {
            if (c.label.equals(label)) {
                return c;
            }
        }
        return INVALID;
    }
}
