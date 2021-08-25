public abstract class Command {

    abstract void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException;
    abstract boolean isExit();
}
