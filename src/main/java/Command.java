/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-Enums
 *
 * Description:
 * Extends the Task Class which where it is a task that needs
 * to be done before a specific time
 *
 * @author Keith Tan
 */
public enum Command {
    UNRECOGNISED,
    LIST,
    DONE,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    BYE;

    /**
     * Convert input string to command.
     *
     * @param input Command issued by user in String format.
     * @return respective command.
     */
    public static Command changeToCommand(String input) {
        String inputLowerCase = input.toLowerCase();
        Command result;
        switch (inputLowerCase) {
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
            case "bye":
                result = Command.BYE;
                break;
            case "delete":
                result = Command.DELETE;
                break;
            default:
                result = Command.UNRECOGNISED;
                break;
        }

        return result;
    }

    /**
     * Convert Command to String
     *
     * @param input Command issued by user in String format.
     * @return respective command in string format.
     */
    public static String changeToString(Command input) {
        String result;
        switch (input) {
            case LIST:
                result = "list";
                break;
            case DONE:
                result = "done";
                break;
            case TODO:
                result = "todo";
                break;
            case DEADLINE:
                result = "deadline";
                break;
            case EVENT:
                result = "event";
                break;
            case BYE:
                result = "bye";
                break;
            case DELETE:
                result = "delete";
                break;
            default:
                result = "unrecognised";
                break;
        }

        return result;
    }

}
