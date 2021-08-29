public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
