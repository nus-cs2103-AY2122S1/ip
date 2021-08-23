package commands;
import tasks.TaskList;

public abstract class Command {
    public abstract String execute(TaskList taskManager) throws DukeException;
}
