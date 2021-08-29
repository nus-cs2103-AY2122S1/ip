import java.util.ArrayList;
import java.util.List;

public class TaskList {
    
    
    private final  List<Task> items = new ArrayList<>(100);
    
    //public TaskList()
    
    public void markDone(int n){
        items.get(n).markAsDone();
    }
    
    private void addTask(Task task){
        items.add(task);
    }
    
    private Task deleteTask(int task){
        return items.remove(task - 1);
    }
    
    private Task getTask(int task){
        return items.get(task - 1);
    }
    

    
    
}
