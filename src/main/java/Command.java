public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui) throws DukeException;
    public boolean isExit() {
        return false;
    }
}