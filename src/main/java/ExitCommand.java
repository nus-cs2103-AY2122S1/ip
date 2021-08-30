public class ExitCommand extends Command{
    public ExitCommand(TaskList taskList, Storage storage, Ui ui) {
        super(taskList, storage, ui);
    }

    @Override
    public void runCommand() {
        ui.bye();
        storage.save(taskList.getList());
    }
}
