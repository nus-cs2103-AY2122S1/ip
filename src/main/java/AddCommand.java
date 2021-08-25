public class AddCommand extends Command {
    TaskList.TaskType taskType;
    String taskDescription;
    
    public AddCommand(TaskList.TaskType taskType, String description) {
        this.taskType = taskType;
        this.taskDescription = description;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.addTask(taskType, taskDescription);
        
        ui.showMessage("I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.getListSize() + " tasks in the list.");
        
        storage.save(tasks.getListData());
    }

    public boolean isExit() {
        return false;
    }
}
