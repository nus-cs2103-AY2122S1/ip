package duke.commands;

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
        return new CommandResult("Bye bye");
    }

}
