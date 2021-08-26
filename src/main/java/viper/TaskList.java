package viper;

import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * contains the list e.g., it has operations to add/delete tasks in the list
 */

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void addTask(Task newTask) throws IOException {
        tasks.add(newTask);
    }
    
    public Task getTask(int index) {
        return tasks.get(index);
    }
    
    public int getSize() {
        return tasks.size();
    }
    
    public void deleteTask(int index) {
        if (index < tasks.size()) {
            tasks.remove(index);
        }
    }
    
    public void doneTask(int index) {
        if (index < tasks.size()) {
            tasks.get(index).setDone();
        }
    }
}
