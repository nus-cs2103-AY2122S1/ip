public class InvalidCommand extends Command {
    public InvalidCommand() {};

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        ui.notifyBadCommand();
        return;
    }
}
