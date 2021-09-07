package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class SortCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        StringBuilder response = new StringBuilder("Okay I have sorted your tasks:\n");
        tasks.sort();
        response.append(ui.printStartList());
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            response.append(ui.printTask(task, i + 1));
        }
        return response.toString();
    }
}
