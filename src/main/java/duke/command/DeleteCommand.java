package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;

    /** Constructor of DeleteCommand class */
    public DeleteCommand(int index) {
        this.index = index;
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
        tasks.deleteTask(this.index);
        storage.writeToFile(tasks);
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
}
