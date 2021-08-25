public class DeleteCommand extends Command {
    private Integer taskNum;
    
    public DeleteCommand(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskNum);

        ui.showMessage("Ok, I've deleted this task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.getListSize() + " tasks in the list.");
        
        storage.save(tasks.getListData());
    }

    public boolean isExit() {
        return false;
    }
}
