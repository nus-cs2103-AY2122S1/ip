import java.util.ArrayList;
import java.util.List;

public class TaskList {
    
    
    private List<Task> items = new ArrayList<>(100);
    
    public TaskList(List<Task> items){
        this.items = items;
    }

    public void list(){
        for(int i = 1; i <= items.size(); i++){
            System.out.println(i + ". " + items.get(i-1).toString());
        }
        System.out.println("");
    }
    
    public void markDone(int n){
        items.get(n).markAsDone();
    }
    
    public void addTask(Task task){
        items.add(task);
    }
    
    public Task deleteTask(int task){
        return items.remove(task - 1);
    }
    
    public Task getTask(int task){
        return items.get(task - 1);
    }

    public int getSize(){
        return items.size();
    }


    

    
    
}
