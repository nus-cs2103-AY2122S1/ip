package duke;
/**
 * This encapsulates all Commands that can be understood by Duke.
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
     * Reads user input in form of a String and returns a Command
     * @param input String that is entered by the user.
     * @return A Command that is the interpreted user command.
     */
    public static Command readInput(String input) {
        String newInput = input.toLowerCase();
        Command newCommand;
        switch (newInput) {
        case "list":
            newCommand = Command.LIST;
            break;
        case "done":
            newCommand = Command.DONE;
            break;
        case "todo":
            newCommand = Command.TODO;
            break;
        case "deadline":
            newCommand = Command.DEADLINE;
            break;
        case "event":
            newCommand = Command.EVENT;
            break;
        case "delete":
            newCommand = Command.DELETE;
            break;
        case "bye":
            newCommand = Command.BYE;
            break;
        default:
            newCommand = Command.UNRECOGNISED;
            break;
        }
        return newCommand;
    }
}
