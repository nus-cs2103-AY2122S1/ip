package duke.classes.commands;

public class HelpCommand extends Command {
    /**
     * Class Constructor
     */
    public HelpCommand() {
    }

    /**
     * Prints the user guide for Duke
     *
     * @return String to be output by Duke
     */
    public String execute() throws IllegalArgumentException {
        return "View https://tchiong.github.io/ip/ for details on commands";
    }
}
