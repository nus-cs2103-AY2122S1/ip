public class DeleteCommand extends Command {
    private int target;
    
    public DeleteCommand(int target) {
        this.target = target;
    }
    
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) throws DukeException{
    // Check for valid task number provided
        if (this.target < 1 || this.target > taskList.getTotalTasks()) {
            throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
        }

        Task removed = taskList.deleteTask(target);
        ui.showRemovedTask(taskList, removed);
    }
}
