import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand() {
        isExit = false;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showList();
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.getTask(i));
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }
    }
}
