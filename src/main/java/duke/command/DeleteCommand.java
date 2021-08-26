package duke.command;

import duke.DukeException;
import duke.tasks.Task;
import duke.TaskList;
import duke.ui.Ui;

/**
 * A DeleteCommand class encapsulates the instructions for Duke to delete a task.
 */
public class DeleteCommand extends Command {
    private int TaskNo;

    public DeleteCommand(int taskNo) {
        this.TaskNo = taskNo;
    }

    /**
     * executes the command on the specified tasklist
     *
     * @param taskList tasklist to be operated on
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList) throws DukeException {
        Task deletedTask = taskList.deleteTask(TaskNo); //throws Duke Exception
        Ui.showDeletedTask(deletedTask);
        Ui.showTaskCount(taskList);
    }

    /**
     * returns the type of command
     *
     * @return delete
     */
    @Override
    public String getType() {
        return "delete";
    }
}