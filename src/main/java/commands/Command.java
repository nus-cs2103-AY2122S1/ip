package commands;
import tasks.TaskList;
import exceptions.DukeException;

public abstract class Command {
    public abstract String execute(TaskList taskManager) throws DukeException;
}
