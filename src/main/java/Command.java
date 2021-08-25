abstract class Command {
    abstract void execute(TaskList task, Ui ui, Storage storage) throws DukeException, InvalidDirectoryException;
    abstract boolean isExit();
}
