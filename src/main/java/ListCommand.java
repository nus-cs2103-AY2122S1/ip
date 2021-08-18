public class ListCommand extends Command {

    public String execute(TaskManager taskManager) {
        return "Here are the tasks in your list:\n" + taskManager.toString();
    }
}
