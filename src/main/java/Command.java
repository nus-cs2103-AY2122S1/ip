public abstract class Command {
    abstract public boolean isExit();
    abstract public void execute(TaskList taskList, Ui ui, Storage storage);
}
