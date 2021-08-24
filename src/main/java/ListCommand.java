public class ListCommand extends Command{
    public static final String INSTRUCTION = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
