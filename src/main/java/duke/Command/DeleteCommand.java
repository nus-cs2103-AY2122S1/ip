package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(index);

        String result = "";
        result += ("Deleted:\n" + task.getDescription() + "\n");
        result += ("There are " + tasks.size() + " tasks remaining in the list");
        storage.save(tasks);
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
