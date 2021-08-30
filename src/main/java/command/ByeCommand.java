package command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents the command that closes the program.
 */
public class ByeCommand extends Command {
    private final Ui ui;

    /**
     * Constructor for a ByeCommand.
     */
    public ByeCommand() {
        super(true);
        ui = new Ui();
    }

    /**
     * Executes the command of closing the program.
     *
     * @param taskList TaskList not used in this execution.
     */
    @Override
    public void execute(TaskList taskList) {
        ui.showBye();
    }
}
