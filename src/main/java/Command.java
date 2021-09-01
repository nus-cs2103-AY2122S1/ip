public abstract class Command {
    public abstract void execute(TaskList ls, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
