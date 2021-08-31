package duke.logic.commands;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @return A bye bye message as the result of execution.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult("Closing...");
    }

    /**
     * Checks if the user wants to exit.
     *
     * @param userInput The user text input.
     * @return true if the input matches the exit command, false otherwise.
     */
    public static boolean isExit(String userInput) {
        return "bye".equals(userInput);
    }

}
