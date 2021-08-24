package commands;
import tasks.TaskList;
import exceptions.DukeException;

public class InvalidCommand extends Command {
    public String execute(TaskList taskManager) throws DukeException {
        throw new DukeException("OOPS!!! Please enter a valid command");
    }
}
