public abstract class Command {

    abstract void execute(ToDo taskList, Ui ui, Storage storage);
    abstract boolean isExit();
}
