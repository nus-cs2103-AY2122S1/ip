public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayExitMessage();
        System.exit(0);
    }
}
