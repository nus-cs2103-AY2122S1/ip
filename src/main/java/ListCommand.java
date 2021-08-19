public class ListCommand extends Command {
    public static final String KEYWORD = "list";

    public String execute(TaskManager taskManager) {
        return "Here are the tasks in your list:\n" + taskManager.toString();
    }
}
