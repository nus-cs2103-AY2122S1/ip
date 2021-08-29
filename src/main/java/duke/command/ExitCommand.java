package duke.command;

import duke.Duke;
import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

/**
 * This class encapsulates an "Exit" command from Duke.
 * Extends Command class.
 */
public class ExitCommand extends Command {

    private Duke duke;

    public ExitCommand(TaskList taskList, Storage storage, Ui ui, Duke duke) {
        super(taskList, storage, ui);
        this.duke = duke;
    }

    @Override
    public void execute() {
        duke.triggerExit();
        ui.showFarewell();
    }
}
