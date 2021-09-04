public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task){
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui u, Storage storage){
        tasks.addTask(task);
        u.displayTaskAdded(task, tasks.size() - 1);
    }
}
