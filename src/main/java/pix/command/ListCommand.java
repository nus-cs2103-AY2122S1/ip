package pix.command;

import pix.storage.Storage;
import pix.task.TaskList;
import pix.ui.Ui;

public class ListCommand extends Command {
    /**
     * Constructor for the list command.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Triggers the list command which adds a task to the task list.
     *
     * @param storage Storage class to store the data in.
     * @param taskList Task list class that has the task list to write from.
     * @param ui Ui class to display the exit message
     *
     * @return Returns the list of tasks to display.
     */
    @Override
    public String trigger(Storage storage, TaskList taskList, Ui ui) {
        return taskList.displayList();
    }
}
