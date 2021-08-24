public class DoneCommand extends Command{
    private int target;
    
    public DoneCommand(int target) {
        this.target = target;
    }
    
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) throws DukeException {
        // Validate target index
        if (this.target > taskList.getTotalTasks() || this.target < 1) {
            throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
        }
        Task completed = taskList.markAsDone(target);
        ui.showCompletedTask(completed);
    }
}
