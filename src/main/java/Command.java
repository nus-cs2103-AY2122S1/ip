/**
 * This class parses and stores a command from the user.
 *
 * @author Chen Yanyu
 */

class Command {
    private Action action;
    private String argument;

    /**
     * Public Constructor for Command that processes the input
     * @param input the input typed by the user
     */
    public Command(String input) {
        this.action = toCommand(input);
        this.argument = toArgument(input);
    }

    /**
     * getter for the Action.
     * @return the String representing action
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * getter for the Argument.
     * @return the String representing the argument
     */
    public String getArgument() {
        return this.argument;
    }

    private static String toArgument(String input) {
        String action = input.split(" ")[0];
        if (action.length() == input.length()) {
            return "";
        } else {
            return input.substring(action.length() + 1);
        }
    }

    private static Action toCommand(String input) {
        String action = input.split(" ")[0];

        switch (action) {
            case "bye":
                return Action.BYE;
            case "list":
                return Action.LIST;
            case "done":
                return Action.DONE;
            case "delete":
                return Action.DELETE;
            case "todo":
                return Action.TODO;
            case "deadline":
                return Action.DEADLINE;
            case "event":
                return Action.EVENT;
            default:
                return Action.UNKNOWN;
        }
    }
}
