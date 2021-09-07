package duke.logic.commands;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    private static final String EXIT_MSG = "Closing...";

    /**
     * Exits the program.
     *
     * @return A bye bye message as the result of execution.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_MSG);
    }

    /**
     * Checks if the user wants to exit.
     *
     * @param userInput The user text input.
     * @return true if the input matches the exit command, false otherwise.
     */
    public static boolean isExit(String userInput) {
        CommandCode cc = CommandCode.getCommandCode(userInput);
        if (cc == null) {
            return false;
        } else {
            return cc.equals(CommandCode.BYE);
        }
    }
}
