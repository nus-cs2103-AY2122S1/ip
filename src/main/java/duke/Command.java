package duke;
public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
