package duke.command;

import duke.util.Ui;

/**
 * Class that represents the Command to exit from the program.
 *
 * @author Benedict Chua
 */
public class ExitCommand extends Command {
    public ExitCommand() { }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() {
        Ui.bidFarewell();
    }
}
