public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);
        ui.showBye();
    }

    @Override
    public boolean isExit() { 
        return true; 
    }
}
