package duke.command;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class DeadlineCommand extends Command {
    protected static final String COMMAND = "deadline";

    private Deadline deadline;

    protected DeadlineCommand(String remainingText) throws DukeException {
        deadline = Deadline.createNewDeadline(remainingText, false);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert deadline != null : "deadline should not be null";
        return taskList.addTask(deadline);
    }
}
