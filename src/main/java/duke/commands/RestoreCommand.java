package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents the command when the user wants to restore a certain archived task back to the main task list.
 */
public class RestoreCommand extends Command {
    private int taskNum;

    public RestoreCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command to restore the specified archived task to the main task list.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     * @return Message indicating whether the task has been successfully restored.
     */
    public String execute(Storage storage, Ui ui) {
        int archiveTaskListLen;
        int archiveLenBeforeDelete;
        int taskListLen;

        try {
            taskListLen = storage.taskListLen();
            if (storage.archiveListLen() >= TaskList.MAX_TASKS) {
                return ui.maxTaskReachedMessage();
            }

            // Delete from archive list
            archiveTaskListLen = storage.archiveListLen();
            if (taskNum + 1 > archiveTaskListLen) {
                return ui.missingTaskMessage(taskNum + 1);
            }
            archiveLenBeforeDelete = taskListLen;
            Task taskToRestore = storage.deleteArchivedTask(taskNum);
            storage.saveToArchive();
            assert(archiveLenBeforeDelete == storage.archiveListLen() + 1)
                    : "Task has not been deleted properly from archive during execution of RestoreCommand.";

            // Add to main task list
            storage.addTask(taskToRestore);
            storage.saveToFile();
            assert(storage.taskListLen() == taskListLen + 1)
                    : "Task has not been added properly to main task list during execution of ArchiveCommand.";
        } catch (IndexOutOfBoundsException e) {
            return Ui.showErrorMessage("Please enter a valid index!");
        }

        return ui.tasksRestoredMessage();
    }
}
