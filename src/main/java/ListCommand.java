public class ListCommand extends Command {

    public ListCommand() {
        // nothing here
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showList(ui);
    }
}
