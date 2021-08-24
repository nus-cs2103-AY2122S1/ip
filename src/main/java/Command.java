import Exceptions.DukeException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean hasExited() {
        return false;
    }
}
