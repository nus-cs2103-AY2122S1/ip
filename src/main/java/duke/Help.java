package duke;

public class Help implements GeneralCommand {

    private static final String INSTRUCTIONS = "Welcome to Duke!\n"
            + "There are 3 different Tasks namely; todo, event and deadline\n"
            + "Other commands include 'done', 'delete', 'find', 'list' and 'undo'";

    public Help() {

    }

    @Override
    public String execute() {
        return INSTRUCTIONS;
    }
}
