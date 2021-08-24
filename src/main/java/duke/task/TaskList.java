package duke.task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
    
    public int size() {
        return this.tasks.size();
    }
    
    public Task get(int idx) {
        return this.tasks.get(idx);
    }
    
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    
    public Task remove(int idx) {
        return this.tasks.remove(idx);
    }
    
    public void add(Task task) {
        this.tasks.add(task);
    }
    
}
