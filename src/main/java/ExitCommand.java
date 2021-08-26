public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
    }

    @Override
    public boolean isExist() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.exit();
        storage.save(tasks.getList());
    }
}