import java.util.ArrayList;
import tasks.*;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    // 1. adding a task to the list

    public void addTask(Task task){
        tasks.add(task);
    }

    // 2. getting a task in a specified index
    public Task getTask(int index){
        return tasks.get(index);
    }

    // 3. removing a task from the specified index
    public void removeTask(int index){
        tasks.remove(index);
    }

    // 4. clearing tasklist
    public void clearTaskList(){
        tasks.clear();
    }

    // 5. checking number of tasks in tasklist
    public int numberOfTasks(){
        return tasks.size();
    }

}
