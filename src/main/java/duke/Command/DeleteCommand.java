package duke.Command;

import duke.*;

public class DeleteCommand extends Command{

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(index);
        ui.print("Deleted:\n" + task.getDescription());
        ui.print("There are " + tasks.size() + " tasks remaining in the list");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
