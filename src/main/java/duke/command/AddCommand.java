package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * The class to represent a command to add tasks.
 */
public class AddCommand extends Command {

    /** The task to be added */
    private Task task;
    private Integer index = null;
    private boolean isAlreadyUndone = false;

    /** Constructor of AddCommand class */
    public AddCommand(Task t) {
        this.task = t;
    }

    /** Constructor of AddCommand class */
    public AddCommand(Task t, int index) {
        this.task = t;
        this.index = index;
        this.isAlreadyUndone = true;
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
        String output;
        if (this.index == null) {
            output = tasks.addTask(this.task);
        } else {
            output = tasks.addTask(this.task, index);
        }
        storage.writeToFile(tasks);
        return output;
    }

    /**
     * Method to determine if two instances of AddCommand are equal
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the two AddCommand instances are equal
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof AddCommand) && this.task.equals((((AddCommand) obj).task));
    }

    /**
     * The method to return whether this command comes from an "undo" command.
     *
     * @return boolean indicating whether this command comes from an "undo" command
     */
    public boolean isAlreadyUndone() {
        return this.isAlreadyUndone;
    }
}
