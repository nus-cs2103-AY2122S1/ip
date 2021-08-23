public interface Command {
    boolean isRunning();
    void execute(TaskList t, Ui ui, Storage storage) throws DukeException;
}
