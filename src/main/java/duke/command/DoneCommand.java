package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;


/**
 * A DoneCommand class that extends from the Command class.
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class DoneCommand extends Command{

    String parameter;

    /**
     * A constructor to initialize a done command.
     * @param parameter the parameter of the done command, should be a task number
     */
    public DoneCommand(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Execute a done command.
     * mark a task from the task list as done.
     * @param taskList The task list to execute the command on
     * @param ui The user interface to display the reply
     * @param storage The place to store the session
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (!parameter.matches("\\d+")) {
            // Invalid parameter
            throw new DukeException("OOPS!!! Invalid task number.");
        }
        Task task = taskList.getTask(Integer.parseInt(parameter));
        taskList.markAsDone(Integer.parseInt(parameter));
        storage.save(taskList);
        ui.printStringInBox(String.format("Nice! I've marked this task as done:\n  %s %s",
                task.getStatusIcon(), task.getDescription()));
    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
