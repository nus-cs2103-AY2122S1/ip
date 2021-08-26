public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        boolean allDone;
        allDone = taskList.printItems();
        if (allDone) {
            ui.printCompleted();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
