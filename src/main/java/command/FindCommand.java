package command;

import exception.PixException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Triggers the find command which finds all tasks which contains the keyword in the task list.
     *
     * @param storage Storage class to store the data in.
     * @param taskList Task list class that has the task list to write from.
     * @param ui Ui class to display the exit message
     *
     * @return Returns the list of tasks that contain the keyword.
     */

    @Override
    public String trigger(Storage storage, TaskList taskList, Ui ui) throws PixException {
        return taskList.findTasks(keyword);
    }
}