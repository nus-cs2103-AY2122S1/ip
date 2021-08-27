package Pix.command;

import Pix.storage.Storage;
import Pix.task.Task;
import Pix.task.TaskList;
import Pix.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for the add command.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Triggers the add command which adds a task to the task list.
     *
     * @param storage Storage class to store the data in.
     * @param taskList Task list class that has the task list to write from.
     * @param ui Ui class to display the exit message
     *
     * @return Returns the message to display.
     */
    @Override
    public String trigger(Storage storage, TaskList taskList, Ui ui) {
        return taskList.addTask(this.task, storage);
    }
}