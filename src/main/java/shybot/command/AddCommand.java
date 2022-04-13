package shybot.command;

/**
 * AddCommand class encapsulates command to add new task.
 */
public abstract class AddCommand extends Command {
    /**
     * Body of the command
     */
    protected String command;

    /**
     * Constructs an AddCommand with the specified command.
     *
     * @param command Body of the command.
     */
    public AddCommand(String command) {
        this.command = command;
    }
}
