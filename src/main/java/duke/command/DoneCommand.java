package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumException;
import duke.task.TaskList;

/**
 * The DoneCommand is given when a Task needs to be marked as done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    private static final String SUCCESS_MSG = "Nice! I've marked this task as done:\n  ";
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the specified Task corresponding to the given index in the TaskList as done.
     * Also saves the updated TaskList to taskList.txt
     *
     * @param tasks the given TaskList.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException when the index given is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (taskNum >= tasks.numTasks()) {
            throw new InvalidTaskNumException(taskNum + 1);
        }
        tasks.markTask(taskNum);
        storage.save(tasks);
        return SUCCESS_MSG + tasks.getTask(taskNum);
    }
}
