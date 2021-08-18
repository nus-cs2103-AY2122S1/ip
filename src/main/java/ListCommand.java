public class ListCommand extends Command {

    public String execute(TaskManager taskManager) {
        return taskManager.toString();
    }
}
