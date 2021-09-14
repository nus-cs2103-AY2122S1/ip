package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * The class to represent a command to delete tasks.
 */
public class DeleteCommand extends Command {

    /** index of task to delete */
    private int index;

    private TaskList tasks;

    private Task deletedTask;
    private boolean isAlreadyUndone = false;

    /** Constructor of DeleteCommand class */
    public DeleteCommand(int index, TaskList tasks) {
        this.index = index;
        this.tasks = tasks;
    }

    public DeleteCommand(int index, TaskList tasks, boolean isAlreadyUndone) {
        this.index = index;
        this.tasks = tasks;
        this.isAlreadyUndone = isAlreadyUndone;
    }

    /**
     * Method to determine if the command is an exit command
     *
     * @return whether it is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Method to carry out the command
     *
     * @param tasks the list of tasks to be modified
     * @param storage the storage utility for the program
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String output = tasks.deleteTask(this.index);
        storage.writeToFile(tasks);
        deletedTask = tasks.getMostRecentlyDeletedTask();
        return output;
    }

    /**
     * Method to determine if two instances of DeleteCommand are equal
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the two DeleteCommand instances are equal
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DeleteCommand) && (this.index == ((DeleteCommand) obj).index);
    }

    public Task getDeletedTask() {
        return this.deletedTask;
    }

    public int getDeletedIndex() {
        return this.index;
    }

    public boolean isAlreadyUndone() {
        return this.isAlreadyUndone;
    }
}
