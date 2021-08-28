package duke;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("All tasks:");
        ui.print(tasks.allTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
