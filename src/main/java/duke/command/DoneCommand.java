package duke.command;

import duke.DukeException;
import duke.tasks.Task;
import duke.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int TaskNo;

    public DoneCommand(int taskNo) {
        this.TaskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList) throws DukeException {
        Task completedTask = taskList.doneTask(TaskNo); //throws duke.DukeException
        Ui.showDoneTask(completedTask);
    }
    @Override
    public String getType() {
        return "done";
    }
}
