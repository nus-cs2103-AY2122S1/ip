import java.io.IOException;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        isExit = false;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (index >= tasks.getSize()) {
            throw new DukeException("OOPS!! task index is invalid");
        }
        tasks.updateStatus(index);
        ui.showDone(tasks.getTask(index));

        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }
    }
}
