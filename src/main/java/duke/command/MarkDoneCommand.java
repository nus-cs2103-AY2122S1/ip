package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.Ui;

/**
 * A command which aims to mark a task as done.
 */
public class MarkDoneCommand extends Command {
    private final int index;

    /**
     * Makes a MarkDoneCommand that marks a task as done.
     *
     * @param index the index of the task to be marked done.
     */
    public MarkDoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as done.
     *
     * @param tasks current list of task.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, FileManager fileManager) throws DukeException {
        tasks.markDone(this.index);
        ui.showMarkDone(tasks.getTask(this.index));
        fileManager.updateTaskList(tasks, ui);
    }

    /**
     * Returns if the function is a exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
