public class ListCommand extends Command {

    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        ui.printTaskList(ls);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
