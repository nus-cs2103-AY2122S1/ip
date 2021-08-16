package main.java;

/**
 * StringToCommand converts a string to its respective command.
 *
 * @author Zhen Xuan (Tutorial Group 04)
 * @version CS2103T AY21/22 S2
 */
public class StringToCommand {

    /**
     * This method converts a string to its respective command.
     * @param str the string to be converted
     * @return the command
     */
    protected static Command convert(String str) {
        switch (str) {
            case "bye":
                return Command.BYE;
            case "list":
                return Command.LIST;
            case "done":
                return Command.DONE;
            case "delete":
                return Command.DELETE;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            default:
                return Command.INVALID;
        }
    }
}
