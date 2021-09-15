package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Task;
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
    public String execute(TaskList taskList) throws DukeException {
        Task deletedTask = taskList.deleteTask(TaskNo); //throws Duke Exception
        String output = Ui.showDeletedTask(deletedTask) + Ui.showTaskCount(taskList);
        return output;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            return ((DeleteCommand) obj).TaskNo == TaskNo;
        } else {
            return false;
        }
    }
}
