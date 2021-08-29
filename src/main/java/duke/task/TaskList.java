package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Active list structure used to store all active tasks
 *
 * @author mrmrinal
 */
public class TaskList {
    
    
    private List<Task> items = new ArrayList<>(100);

    /**
     * Creates new TaskList instance
     *
     * @param items Task List autofilled from the storage
     */
    public TaskList(List<Task> items){
        this.items = items;
    }

    /**
     * Lists all the Tasks down in numerical form
     */
    public void list(){
        for(int i = 1; i <= items.size(); i++){
            System.out.println(i + ". " + items.get(i-1).toString());
        }
        System.out.println("");
    }

    /**
     * Marks a specific task as done
     *
     * @param n the position of task in the List
     */
    public void markDone(int n){
        items.get(n).markAsDone();
    }

    /**
     * Add a new task to the List
     *
     * @param task task that user wants to add
     */
    public void addTask(Task task){
        items.add(task);
    }

    /**
     * Delete a specific task on the list
     *
     * @param task Position of task that user wants to delete
     */
    public Task deleteTask(int task){
        return items.remove(task - 1);
    }

    /**
     * Get a specific task on the list
     *
     * @param task Position of task that user wants to obtain
     */
    public Task getTask(int task){
        return items.get(task - 1);
    }

    /**
     * Get total number of tasks on the list
     */
    public int getSize(){
        return items.size();
    }

}
