package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * The class to represent a command to mark tasks as undone.
 * Only for undo-ing a DoneCommand, and not for users to mark tasks as Undone yet.
 */
public class UndoneCommand extends Command {

    /** index of task to be marked as undone */
    private int index;

    /** Constructor of UndoneCommand class */
    public UndoneCommand(int index) {
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
        String output = tasks.markTaskAsUndone(this.index);
        storage.writeToFile(tasks);
        return output;
    }

    /**
     * Determines if two instances of UndoneCommand are equal.
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the two UndoneCommand instances are equal
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof UndoneCommand) && (this.index == ((UndoneCommand) obj).index);
    }
}
