package duke.command;

import duke.DukeException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a Command that marks a Task as Done.
 */
public class DoneCommand extends Command {
    /**
     * The task number to be marked as done.
     */
    private final Integer taskNum;

    /**
     * Constructs a done command with a task number.
     *
     * @param taskNum The task number to be marked as done.
     */
    public DoneCommand(Integer taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the task done command.
     *
     * @param tasks   The task list to execute the command on.
     * @param storage The storage for the tasks.
     * @return a string output.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.markTaskDone(taskNum);

        storage.save(tasks.getListData());
        return formatOutput("Good work! I've marked this task as done:", task.toString());
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
