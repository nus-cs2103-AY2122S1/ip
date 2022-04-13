package duck.command;

import duck.TaskList;
import duck.Ui;

/**
 * Represents the command that closes the program.
 */
public class ByeCommand extends Command {
    private final Ui ui;

    /**
     * Constructor for a ByeCommand.
     */
    public ByeCommand() {
        super();
        ui = new Ui();
    }

    /**
     * Executes the command of closing the program.
     *
     * @param taskList TaskList not used in this execution.
     * @return String representing the closing of the program.
     */
    @Override
    public String execute(TaskList taskList) {
        return ui.showBye();
    }
}
