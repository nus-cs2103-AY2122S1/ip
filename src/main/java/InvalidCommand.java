public class InvalidCommand extends Command {
    public String execute(TaskManager taskManager) throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
