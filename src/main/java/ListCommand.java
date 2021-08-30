public class ListCommand extends Command{
    public ListCommand(TaskList taskList, Storage storage, Ui ui) {
        super(taskList, storage, ui);
    }

    @Override
    public void runCommand() {
        ui.listTaskMessage(taskList);
    }
}
