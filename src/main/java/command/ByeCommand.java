package command;

import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    
    public ByeCommand() {
        super(true);
    }

    /**
     * Executes the command of closing the program.
     *
     * @param ui Ui used to display the program closing message.
     * @param taskList TaskList not used in this execution.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showBye();
    }
}
