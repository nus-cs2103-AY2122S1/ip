package duke;

/**
 * Help class to maintain the help command.
 */
public class Help implements GeneralCommand {

    private static final String INSTRUCTIONS = "Welcome to Duke!\n"
            + "There are 3 different Tasks namely; todo, event and deadline\n"
            + "Other commands include 'done', 'delete', 'find', 'list' and 'undo'";

    /**
     * Constructs Help object.
     */
    public Help() {

    }

    /**
     * Executes the command help.
     *
     * @return String to be printed on GUI.
     */
    @Override
    public String execute() {
        return INSTRUCTIONS;
    }
}
