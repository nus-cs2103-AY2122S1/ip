public class ExitCommand extends Command {

    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("This should not be executed");
    }
}
