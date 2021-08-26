package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Representation of remove command of Duke.
 */
public class RemoveCommand extends Command {
    private final int indexToRemove;

    /**
     * Constructor for RemoveCommand.
     *
     * @param indexToRemove Index of TaskList to be removed.
     */
    public RemoveCommand(int indexToRemove){
        this.indexToRemove = indexToRemove;
    }

    /**
     * Checks if this is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the RemoveCommand.
     *
     * @param tasks TaskList to remove command at index indexToRemove from.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task toRemove = tasks.remove(this.indexToRemove);
        String message = "Noted. I've removed this task:\n" + toRemove + "\nNow you have "
                + tasks.getSize() + " tasks in the list";
        ui.print(message);
    }
}
