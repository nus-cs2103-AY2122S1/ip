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
     * Display all commands
     */
    @Override
    public void execute() {
        displayHelp();
    }


    /**
     * Lists out all possible commands
     */
    private void displayHelp() {
        System.out.println(new CommandAddTodo(null, null));
        System.out.println(new CommandAddDeadline(null, null));
        System.out.println(new CommandAddEvent(null, null));
        System.out.println(new CommandDone(null, 0));
        System.out.println(new CommandDelete(null, 0));
        System.out.println(new CommandList(null, null));
        System.out.println(new CommandExit());
    }
}
