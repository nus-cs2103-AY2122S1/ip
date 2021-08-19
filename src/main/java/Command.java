public abstract class Command {
    public abstract String execute(TaskManager taskManager) throws DukeException;
}
