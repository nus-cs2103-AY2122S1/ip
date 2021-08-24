package duke;

public class DeleteCommand extends Command {
    private int deletedTaskIndex;// the index in the list

    public DeleteCommand(int deletedTaskIndex){
        this.deletedTaskIndex = deletedTaskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task deletedTask = tasks.delete(deletedTaskIndex);
        storage.convertTaskListToFile(tasks);
        ui.deleteTask(deletedTask, tasks.size());
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
