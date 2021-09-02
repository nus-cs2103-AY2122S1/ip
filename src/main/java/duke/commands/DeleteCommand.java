package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Deletes the specified task.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * @param userInput The raw input from the user
     */
    public DeleteCommand(String userInput) {
        this.taskNumber = Integer.parseInt(
                userInput.replaceAll(
                        "[^0-9]",
                        ""));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.deleteTask(this.taskNumber));
        storage.write(tasks.getSaveData());
    }
}
