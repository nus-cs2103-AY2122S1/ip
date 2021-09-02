package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Marks a task as complete.
 */
public class DoneCommand extends Command {
    private final int taskNumber;

    /**
     * @param userInput The raw input from the user
     */
    public DoneCommand(String userInput) {
        this.taskNumber = Integer.parseInt(
                userInput.replaceAll(
                        "[^0-9]",
                        ""));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.markAsDone(this.taskNumber));
        storage.write(tasks.getSaveData());
    }
}
