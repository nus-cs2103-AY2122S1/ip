public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        // Do nothing
    };

    @Override
    public boolean isExit() {
        return true;
    };
}
