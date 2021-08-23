package commands;
import tasks.TaskManager;

public class InvalidCommand extends Command {
    public String execute(TaskManager taskManager) throws DukeException {
        throw new DukeException("OOPS!!! Please enter a valid command");
    }
}
