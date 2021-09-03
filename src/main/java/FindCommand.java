import java.io.IOException;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        isExit = false;
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showFind();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.description.contains(keyword)) {
                System.out.printf("%d.%s\n", i + 1, task);
            }
        }
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }
    }
}
