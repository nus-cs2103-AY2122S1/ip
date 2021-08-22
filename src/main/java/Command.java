public interface Command {
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    boolean isExit();
}
