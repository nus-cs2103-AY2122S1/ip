public class ListCommand extends Command{

    public ListCommand(TaskList tasklist){
        super(tasklist);
    }

    public void execute(){
        super.taskList.listTasks();
    }
}
