package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;

public class DoneCommand implements Command {
    private String description;

    public DoneCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskId = Integer.parseInt(description);
            return tasks.markAsDone(taskId);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("done should be in format: done [TASK NUMBER]");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
