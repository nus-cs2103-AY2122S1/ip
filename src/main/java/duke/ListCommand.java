package duke;

/**
 * A class that handles listing existing tasks.
 */
public class ListCommand implements Command {

    /**
     * Constructor for ListCommand object.
     */
    public ListCommand() {
        super();
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        return ui.getTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
