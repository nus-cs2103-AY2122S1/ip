public abstract class Command {
    public Command() {
    }

    abstract public void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
