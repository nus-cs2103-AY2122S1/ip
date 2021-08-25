package duke.command;

/**
 * Command to exit program
 */
public class CommandExit extends Command {

    /**
     * Constructor
     */
    public CommandExit() {
        this.commandName = "gubbai";
        this.description = "Exits the program";
    }

    /**
     * This class signals to Duke that it should terminate the program
     */
    @Override
    public void execute() {
        return;
    }
}
