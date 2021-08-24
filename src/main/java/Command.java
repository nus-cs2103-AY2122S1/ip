public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
