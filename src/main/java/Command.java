public abstract class Command {
    public abstract void execute(TaskList task, UI userInt, Storage storage)
            throws DukeException;

    public abstract boolean isExit();
}
