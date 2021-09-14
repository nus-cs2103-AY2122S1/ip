package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


/**
 * A DoneCommand class that extends from the Command class.
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class DoneCommand extends Command{

    String parameter;

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
        boolean isValidNumber = parameter.matches("\\d+");
        if (!isValidNumber) {
            // Invalid parameter
            throw new DukeException("OOPS!!! Invalid task number.");
        }
        Task task = taskList.getTask(Integer.parseInt(parameter));
        taskList.markAsDone(Integer.parseInt(parameter));
        storage.save(taskList);
        return String.format("Nice! I've marked this task as done:\n  %s %s",
                task.getStatusIcon(), task.getDescription());
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

