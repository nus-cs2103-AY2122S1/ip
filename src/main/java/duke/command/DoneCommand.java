package duke.command;

import duke.DukeException;
import duke.tasks.Task;
import duke.TaskList;
import duke.ui.Ui;

/**
 * A DoneCommand class encapsulates the instructions for Duke to set a task as done.
 */
public class DoneCommand extends Command {
    private int TaskNo;

    public DoneCommand(int taskNo) {
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
        Task completedTask = taskList.doneTask(TaskNo); //throws DukeException

        Ui.showDoneTask(completedTask);
    }

    /**
     * returns the type of command
     *
     * @return done
     */
    @Override
    public String getType() {
        return "done";
    }
}
