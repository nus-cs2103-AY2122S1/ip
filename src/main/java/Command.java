public interface Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    default public boolean isExit() {
        return false;
    }
}
