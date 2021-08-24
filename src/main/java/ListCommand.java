public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 1; i <= tasks.size(); i++) {
            ui.show("\t" + i + "." + tasks.retrieveTask(i));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
