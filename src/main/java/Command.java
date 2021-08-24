abstract class Command {
    public abstract void exec(TaskList tasks, Ui ui, Storage storage) throws NoListException;

    public boolean isExit() {
        return false;
    }
}
