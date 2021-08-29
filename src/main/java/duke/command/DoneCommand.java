package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private final int taskNo;

    public DoneCommand(String taskNo) throws DukeException {
        try {
            this.taskNo = Integer.parseInt(taskNo);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.taskDone(taskNo);
    }

    public boolean isExit() {
        return false;
    }
}
