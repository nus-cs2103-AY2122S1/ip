package duke.command;

/**
 * Command to display the help window
 */
public class CommandHelp extends Command {

    /**
     * Constructor
     */
    public CommandHelp() {
        this.commandName = "help";
        this.description = "displays a list of possible commands";
        this.arguments = new String[]{};
    }

    /**
     * Displays all commands
     */
    @Override
    public String execute() {
        return displayHelp();
    }


    /**
     * Lists out all possible commands
     */
    private String displayHelp() {
        return new CommandAddTodo(null, null).toString()
                + new CommandAddDeadline(null, null)
                + new CommandAddEvent(null, null)
                + new CommandDone(null, 0)
                + new CommandDelete(null, 0)
                + new CommandList(null, null)
                + new CommandExit();
    }
}
