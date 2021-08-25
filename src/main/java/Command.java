public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
