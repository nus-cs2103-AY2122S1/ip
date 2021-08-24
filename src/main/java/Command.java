abstract public class Command {
    public abstract void execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException;
}
