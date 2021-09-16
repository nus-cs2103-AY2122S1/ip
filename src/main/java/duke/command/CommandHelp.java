package duke.command;

/**
 * Command to display the help window.
 */
public class CommandHelp extends Command {

    /**
     * Constructor for this command.
     */
    public CommandHelp() {
        this.commandName = "help";
        this.description = "displays a list of possible commands";
        this.arguments = new String[]{};
    }

    /**
     * Displays all commands.
     */
    @Override
    public String execute() {
        return displayHelp();
    }


    /**
     * Lists out all possible commands.
     */
    private String displayHelp() {
        return new CommandAddTodo(null, null).toString() + '\n'
                + new CommandAddDeadline(null, null) + '\n'
                + new CommandAddEvent(null, null) + '\n'
                + new CommandDone(null, 0) + '\n'
                + new CommandDelete(null, 0) + '\n'
                + new CommandList(null, null) + '\n'
                + new CommandSort(null, null) + '\n'
                + new CommandHelp() + '\n'
                + new CommandExit();
    }
}
