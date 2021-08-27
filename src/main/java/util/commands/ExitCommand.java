package util.commands;


/**
 * When this command runs, the Duke stops working.
 *
 */
public class ExitCommand implements Command {


    private static boolean isClosed = false;

    /**
     * The constructor of the ExitCommand.
     *
     */
    public ExitCommand() {
    }

    @Override
    public void execute() {
        this.isClosed = true;
    }

    public static boolean isExit() {
        return ExitCommand.isClosed;
    }


}
