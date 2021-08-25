package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Constructs DoneCommand object.
     *
     * @param taskNumber Task number of the task to be marked done.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks done task in Task List and Storage.
     * Send done message to the user.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.doneTask(taskNumber);
        storage.save(tasks);
        ui.showDone(tasks.getTask(taskNumber));
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
