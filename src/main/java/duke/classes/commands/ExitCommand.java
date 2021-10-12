package duke.classes.commands;

public class ExitCommand extends Command {
    /**
     * Class Constructor
     */
    public ExitCommand() {}

    /**
     * Prompts exit from program
     *
     * @return String to be output by Duke
     */
    public String execute() {
        return "Good Bye";
    }
}
