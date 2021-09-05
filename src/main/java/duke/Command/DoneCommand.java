package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.doneTask(this.index);
        storage.save(tasks);
        return ("Marked as done:\n" + task.getDescription());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
