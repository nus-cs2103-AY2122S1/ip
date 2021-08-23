public class ByeCommand extends Command {

    public ByeCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return;        
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
