public class TodoCommand extends Command {
    private String description;
    
    public TodoCommand(String description) {
        this.description = description;
    }
    
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) {
        ToDo task = new ToDo(this.description);
        taskList.addTask(task);
        ui.showAddedTask(taskList, task);
    }
}
