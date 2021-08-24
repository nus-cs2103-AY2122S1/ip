public class AddCommand extends Command{
    private Task taskToBeAdded;

    public AddCommand(Task taskToBeAdded){
        this.taskToBeAdded = taskToBeAdded;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        storage.addTaskToFile(taskToBeAdded);
        tasks.add(taskToBeAdded);
        ui.addTask(taskToBeAdded, tasks.size());
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
