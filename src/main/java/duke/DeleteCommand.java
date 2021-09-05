package duke;

public class DeleteCommand extends Command{
    private int index;
    public DeleteCommand(int index){
        this.index = index;
    }
    public  void execute(TaskList tasks, Ui u, Storage storage){
        tasks.deleteTask(this.index);
        u.displayTaskRemoved(tasks.getTask(index), tasks.size());
    }
}
