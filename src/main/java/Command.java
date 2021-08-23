public interface Command {
    String execute(TaskList tasks) throws DukeException;
}