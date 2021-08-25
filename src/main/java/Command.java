public abstract class Command {
    protected boolean isExit = false;

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws EightBitException;
}
