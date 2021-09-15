package duke;

/**
 * Command to add a Todo Task to the existing list of tasks.
 */
public class HelpCommand extends Command {
    

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException {
        
        return ui.helpMessage();
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
