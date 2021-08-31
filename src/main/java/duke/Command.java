package duke;

/**
 * Command Interface
 */
public interface Command {

    public abstract String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
