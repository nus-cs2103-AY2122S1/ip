package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * TagCommand add tag to task.
 */
public class TagCommand extends Command {
    private String tag;
    private int taskNumber;

    /**
     * Constructs TagCommand object.
     *
     * @param tag Tag to be added.
     * @param taskNumber Task number of the task to be tag.
     */
    public TagCommand(String tag, int taskNumber) {
        assert taskNumber >= 1;
        this.isExit = false;
        this.tag = tag;
        this.taskNumber = taskNumber;
    }

    /**
     * Tag task in Task List and Storage.
     * Return tag message to be sent to user.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @return Tag message to be sent to user.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        tasks.addTag(tag, taskNumber);
        storage.save(tasks);
        return ui.createTagMessage(tag, tasks.getTask(taskNumber));
    }

}
