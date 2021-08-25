public class ExitCommand extends Command{

    ExitCommand() {

    }

    // saves tasklist and calls ui to print bye message
    public void execute(TaskList task, Ui ui, Storage storage) {
        storage.save(task);
        ui.showByeMessage();
    }

    public boolean isExit() {
        return true;
    }
}
