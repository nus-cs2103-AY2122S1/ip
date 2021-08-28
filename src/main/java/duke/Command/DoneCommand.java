package duke.Command;

import duke.*;

public class DoneCommand extends Command{

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.doneTask(this.index);
        ui.print("Marked as done:\n" + task.getDescription());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
