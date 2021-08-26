package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.Ui;
import ligma.task.Task;

import java.io.IOException;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task t = tasks.deleteTask(index);

        try {
            storage.deleteTask(t);
            Ui.printSuccessMessage("deleted:\n " + t
                    + String.format("\n You now have %d task(s).", tasks.getTaskAmt()));
        } catch (IOException e) {
            Ui.printErrorMessage("Failed to delete task from storage: \n" + t);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
