public class HelpCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showHelp();
    }

    public boolean isExit() {
        return false;
    }
}
