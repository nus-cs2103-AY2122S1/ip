package duke.command;

/**
 * The type Exit command that exits the program Duke.
 */
public class ExitCommand extends Command {

    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
