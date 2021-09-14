package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.exception.DukeException;


/**
 * A DeleteCommand class that extends from the Command class.
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class DeleteCommand extends Command{

    String parameter;

    /**
     * A constructor to initialize a delete command.
     * @param parameter the parameter of the delete command, should be a task number.
     */
    public DeleteCommand(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Execute a delete command.
     * Delete a task from the task list.
     * @param taskList The task list to execute the command on.
     * @param storage The place to store the session.
     * @throws DukeException invalid task number.
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
        taskList.deleteTask(Integer.parseInt(parameter));
        storage.save(taskList);
        return String.format("Noted. I've removed this task:\n  %s %s\nNow you have %d tasks in the list.",
                        task.getStatusIcon(), task.getDescription(), taskList.size());
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
