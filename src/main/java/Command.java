public class Command {
    private String action;
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
    public String getAction() {
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
        if (toCommand(input).length() == input.length()) {
            return "";
        } else {
            return input.substring(toCommand(input).length() + 1);
        }
    }

    private static String toCommand(String input) {
        return input.split(" ")[0];
    }
}
