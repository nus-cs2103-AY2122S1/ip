package duke.task;

import java.util.ArrayList;

public class TaskList {
    
    private ArrayList<Task> tasks;

    public TaskList() { 
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    public void add(Task task) {
        tasks.add(task);
    }

}
