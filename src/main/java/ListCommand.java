public class ListCommand extends Command{

    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) {
        ui.showList(tasks);
    }
}
