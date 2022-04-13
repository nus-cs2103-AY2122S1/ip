package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidParameterDukeException;

/**
 * A DoneCommand class that extends from the Command class.
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class DoneCommand extends Command{

    private final String parameter;
    private static final String SUCCESS_MESSAGE = "Nice! I've marked this task as done:\n  %s %s";

    /**
     * A constructor to initialize a done command.
     * @param parameter the parameter of the done command, should be a task number.
     */
    public DoneCommand(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Execute a done command.
     * Mark a task from the task list as done.
     * @param taskList The task list to execute the command on.
     * @param storage The place to store the session.
     * @throws DukeException task does not exist.
     * @return The response
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "task list should not be null.";
        if (!isValidNumber(parameter)) {
            // Invalid parameter
            throw new InvalidParameterDukeException();
        }
        Task task = taskList.getTask(Integer.parseInt(parameter));
        taskList.markAsDone(Integer.parseInt(parameter));
        storage.save(taskList);
        return String.format(SUCCESS_MESSAGE, task.getStatusIcon(), task.getDescription());
    }

    private boolean isValidNumber(String number) {
        return number.matches("\\d+");
    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

