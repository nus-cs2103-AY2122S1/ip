package util.commands;


/**
 * When this command runs, the Duke stops working.
 *
 */
public class ExitCommand implements Command {


    public static boolean isExit = false;

    /**
     * The constructor of the ExitCommand.
     *
     */
    public ExitCommand() {}

    @Override
    public void execute() {this.isExit = true;}


}
