import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to save tasks");
        }
        ui.showExit();
    }
}
