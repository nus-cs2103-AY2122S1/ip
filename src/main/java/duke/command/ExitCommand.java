package duke.command;

import duke.Ui;

/**
 * This class deals with the exit command.
 */
public class ExitCommand extends Command {

    /**
     * Executes instructions according to the Command type (here is exiting the program)
     */
    @Override
    public void execute() {
        Ui.sayBye();
    }
}
