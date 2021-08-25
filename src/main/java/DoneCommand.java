import java.io.IOException;

public class DoneCommand implements Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task t = tasks.markAsDone(index);

        try {
            storage.markDone(t);
            Ui.printSuccessMessage("marked as done:\n " + t);
        } catch (IOException e) {
            Ui.printErrorMessage("Failed to update task in storage: \n" + t);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
