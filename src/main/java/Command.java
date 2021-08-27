public abstract class Command {
    public abstract boolean isExit();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
