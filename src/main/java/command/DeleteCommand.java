package command;

import exception.PixException;
import exception.PixIOException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Triggers the done command which completes a task in the task list.
     *
     * @param storage Storage class to store the data in.
     * @param taskList Task list class that has the task list to write from.
     * @param ui Ui class to display the exit message
     *
     * @return Returns the list of tasks to display.
     */
    @Override
    public String trigger(Storage storage, TaskList taskList, Ui ui) throws PixException {
        try {
            storage.writeToFile(taskList.getTaskList());
        } catch (IOException | PixIOException e) {
            //The format should be set and there shouldn't be any I/O error.
        }

        return taskList.deleteTask(taskNumber);
    }
}