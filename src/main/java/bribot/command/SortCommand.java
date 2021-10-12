package bribot.command;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.Task;
import bribot.task.TaskList;
import bribot.ui.Ui;

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
        storage.save(tasks.getTasks());
        return response.toString();
    }
}
