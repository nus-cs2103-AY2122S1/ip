package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyWord;

    /**
     * Constructs FIndCommand object.
     *
     * @param keyWord Key Word to match the description of task
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Find task in Task List using key word.
     * Send list of matching task to the user.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.findTask(keyWord);
        ui.showFindTask(foundTasks);
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return Boolean whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
