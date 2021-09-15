package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * FindCommand finds keyword present in the task list.
 */
public class FindCommand extends Command {
    private String keyWord;

    /**
     * Constructs FIndCommand object.
     *
     * @param keyWord Key Word to match the description of task
     */
    public FindCommand(String keyWord) {
        this.isExit = false;
        this.keyWord = keyWord;
    }

    /**
     * Find task in Task List using key word.
     * Send list of matching task to the user.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @return Task in string format.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        ArrayList<Task> foundTasks = tasks.findTask(keyWord);
        return ui.createFindTaskMessage(foundTasks);
    }

}
