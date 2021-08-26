package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.deleteTask(index);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
