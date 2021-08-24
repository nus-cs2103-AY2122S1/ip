package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private String response;

    public MarkCommand(String response) {
        this.response = response;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int curr = Integer.parseInt(response.substring(5));
        Task shouldMark = tasks.markElement(curr - 1);
        String stringForm = shouldMark.toString();
        storage.replace(curr - 1, tasks);
        ui.showDone(stringForm);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
