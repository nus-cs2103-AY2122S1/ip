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

    /**
     * Constructor for a ExitCommand.
     * @param taskList TaskList involved in the command.
     * @param storage Storage involved in the command.
     * @param ui Ui involved in the command.
     * @param duke Duke instance involved in the command.
     */
    public ExitCommand(TaskList taskList, Storage storage, Ui ui, Duke duke) {
        super(taskList, storage, ui);
        this.duke = duke;
    }

    /**
     * Executes the Command.
     */
    @Override
    public void execute() {
        duke.triggerExit();
        ui.setMessage("Seeya, press enter again to close.");
    }
}
