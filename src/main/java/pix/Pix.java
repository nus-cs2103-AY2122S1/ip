package pix;

import java.util.ArrayList;

import pix.command.Command;
import pix.command.EmptyCommand;
import pix.exception.PixException;
import pix.storage.Storage;
import pix.task.Task;
import pix.task.TaskList;
import pix.ui.Ui;

/**
 * Pix class to run Pix.
 */
public class Pix {
    private Command lastCommand;
    private Storage storage;
    private TaskList previousTaskList;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Pix.
     *
     * @param filePath The file path to access the task list from.
     */
    public Pix(String filePath) {
        this.ui = new Ui();
        this.lastCommand = new EmptyCommand();
        try {
            this.storage = new Storage(filePath, this);
            this.taskList = new TaskList(storage.load(), storage, this);
            this.previousTaskList = new TaskList(storage.load(), storage, this);
        } catch (PixException e) {
            ui.showLoadingError();
            taskList = new TaskList(storage, this);
            this.previousTaskList = new TaskList(storage, this);
        }
    }

    /**
     * Sets the last command if the last command changed data.
     *
     * @param command Command that was last inputted.
     */
    public void setLastCommand(Command command) {
        this.lastCommand = command;
    }

    /**
     * Gets the last command that the user inputted.
     *
     * @return Returns the last command inputted.
     */
    public Command getLastCommand() {
        return this.lastCommand;
    }

    /**
     * Gets the task list.
     *
     * @return Returns the task list.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Sets the task list.
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList.setTaskList(taskList);
    }

    /**
     * Gets the storage class.
     *
     * @return Returns the storage class.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Undo the last command from the user by swapping the task list with the history of the task list.
     */
    public void undoLastTask() {
        TaskList temp = this.previousTaskList;
        this.previousTaskList = this.taskList;
        this.taskList = temp;
    }
}
