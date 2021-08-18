public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        ui.displayList(tasklist);
        return;
    }
}
