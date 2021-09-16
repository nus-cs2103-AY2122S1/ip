package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;
import duke.tasks.Task;

/**
 * Represents the command when the user wants to mark a task as done.
 */
public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand (int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command and marks the specified task as done.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     * @return Message indicating whether the task has been successfully marked as done.
     */
    public String execute(Storage storage, Ui ui) {
        try {
            if (taskNum + 1 > storage.taskListLen()) {
                return ui.missingTaskMessage(this.taskNum + 1);
            }
            Task taskToMark = storage.getTask(taskNum);
            taskToMark.markDone();
            storage.saveToFile();
            return ui.markedDoneMessage(taskToMark);

        } catch (IndexOutOfBoundsException e) {
            return Ui.showErrorMessage("Please enter a valid index!");
        }
    }

}
