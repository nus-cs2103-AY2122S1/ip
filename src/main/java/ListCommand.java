public class ListCommand implements Command {
    public ListCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    };

    public boolean isExit() {
        return false;
    }
}
