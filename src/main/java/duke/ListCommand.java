package duke;

public class ListCommand extends Command {

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
