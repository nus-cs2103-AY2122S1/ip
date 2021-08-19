/**
 * This is an ExitCommand class that extends Command.
 */
public class ExitCommand extends Command {

    protected ExitCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskList taskList, Storage store, Ui ui)
            throws DukeFileException {
        taskList.safeTasks(store);
        ui.bidFarewell();
    }
}
