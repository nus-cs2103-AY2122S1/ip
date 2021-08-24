public class ListAllCommand extends Command {
    public ListAllCommand(){}

    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) {
        ui.listAllTasks(taskList);
    }
}
