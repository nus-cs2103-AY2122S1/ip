public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.listOut();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
