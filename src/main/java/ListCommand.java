public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.getTasks());
    }

    public boolean isExit() {
        return false;
    }
}
