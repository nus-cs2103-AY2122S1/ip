package katheryne;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import katheryne.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> lst = new ArrayList<>();

    public TaskList() {
        
    }

    void addAll( List<Task> tasks) {
        lst.addAll(tasks);
    }

    public void printList() {
        System.out.println("Here's the list I've stored for you:");
        for (int i = 1; i <= lst.size(); i++) {
            System.out.println(i + ") " + lst.get(i - 1));
        };
    }
    public void add(Task t) {
        lst.add(t);
    }

    /**
     * Removes task from your list
     * 
     * @param index of the task to delete.
     * @return the task which was deleted.
     */
    public Task deleteTask(int index) {
        return lst.remove(index);
    }
    
    public void doTask(int index) {
        lst.get(index).markAsDone();
    }
    
    public Task getTask(int index) {
        return lst.get(index);
    }
    
    public ArrayList<Task> getList() {
        return lst;
    }

    public boolean isEmpty() {
        return lst.isEmpty();
    }

    public int getSize() {
        return lst.size();
    }
}
