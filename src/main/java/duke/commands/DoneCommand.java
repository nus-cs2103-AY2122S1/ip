package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The command indicating that the user wants to mark a task as done.
 */
public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Constructor for DoneCommand.
     *
     * @param taskNum the task number whose corresponding task you want to mark as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Performs the necessary actions that will mark the given task as done, as well
     * as update the persisted data text file.
     *
     * @param tasks the full task list containing all the tasks.
     * @param ui the ui instance.
     * @param storage the storage instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(this.taskNum);
        storage.markPersistedTaskAsDone(this.taskNum, tasks.getTask(this.taskNum));
        ui.showTaskDoneInteraction(tasks.getTask(this.taskNum));
    }
}
