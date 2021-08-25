package duke;

public class ExitCommand extends Command{

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
