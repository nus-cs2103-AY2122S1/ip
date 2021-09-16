package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * The class to represent a command to mark tasks as done.
 */
public class DoneCommand extends Command {

    /** index of task to be marked as done */
    private int index;

    /** Constructor of DoneCommand class */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return whether it is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Carries out the command.
     *
     * @param tasks the list of tasks to be modified
     * @param storage the storage utility for the program
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String output = tasks.markTaskAsDone(this.index);
        storage.writeToFile(tasks);
        return output;
    }

    /**
     * Determines if two instances of DoneCommand are equal.
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the two DoneCommand instances are equal
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DoneCommand) && (this.index == ((DoneCommand) obj).index);
    }

    public int getIndex() {
        return this.index;
    }
}
