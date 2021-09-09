package duke;

public abstract class Command {

    public Command() {
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) throws DukeException;

    public abstract boolean isClosed();
}
