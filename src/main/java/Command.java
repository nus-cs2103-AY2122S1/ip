/**
 * All commands available to Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public enum Command {
    UNRECOGNISED,
    LIST,
    DONE,
    TODO,
    DEADLINE,
    EVENT,
    EXIT;

    /**
     * Convert input command.
     *
     * @param input Command issued by user in String format.
     * @return Corresponding command.
     */
    public static Command convertInput(String input) {
        String lowerCaseInput = input.toLowerCase();
        Command result;
        switch (lowerCaseInput) {
            case "list":
                result = Command.LIST;
                break;
            case "done":
                result = Command.DONE;
                break;
            case "todo":
                result = Command.TODO;
                break;
            case "deadline":
                result = Command.DEADLINE;
                break;
            case "event":
                result = Command.EVENT;
                break;
            case "exit":
                result = Command.EXIT;
                break;
            default:
                result = Command.UNRECOGNISED;
                break;
        }

        return result;
    }
}
