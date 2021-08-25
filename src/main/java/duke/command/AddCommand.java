package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {

    /** The task to be added */
    private Task task;

    /** Constructor of AddCommand class */
    public AddCommand(Task t) {
        this.task = t;
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
     * @param ui the UI for the program
     * @param storage the storage utility for the program
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        storage.writeToFile(tasks);
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
}
