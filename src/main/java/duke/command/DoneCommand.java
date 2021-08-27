package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class represents the command when a user types "done" validly.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor for DoneCommand, which takes in the index of the task to be done on the list.
     *
     * @param index index on the list
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Finishes the task at the index, and saves.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.finishTask(index);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
    }

    /**
     * Returns false as the program should not terminate.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
