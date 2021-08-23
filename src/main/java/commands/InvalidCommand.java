package commands;
import tasks.TaskList;

public class InvalidCommand extends Command {
    public String execute(TaskList taskManager) throws DukeException {
        throw new DukeException("OOPS!!! Please enter a valid command");
    }
}
