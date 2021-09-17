package duke.command;

import duke.data.TaskList;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Bye" Command.
 *
 * @author Wang Hong Yong
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand.
     *
     * @param tasklist Task handler that handles the operation.
     */
    public ByeCommand(TaskList tasklist) {
        super(tasklist);
    }

    /**
     * Executes the "Bye" Command.
     *
     * @return the string representing end of the application
     */
    @Override
    public String execute() {
        return Ui.getGoodbyeMsg();
    }
}


