package commands;

/**
 * A command that represents an invalid command given by the user. This command
 * occurs when the user enter an input that Duke does not recognise.
 */
public class InvalidCommand implements Command {

    /**
     * Displays a message to the user that he has entered an invalid input to Duke.
     */
    @Override
    public boolean execute() {
        System.out.println("Invalid input. Please try again.\n");
        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void invalidArgumentsProvided() {
        // Not needed
    }
}
