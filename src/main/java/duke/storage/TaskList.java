package duke.storage;

import duke.task.Task;

import java.util.List;

public class TaskList {
    /** The current list of tasks */
    private List<Task> tasks;
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void add(Task task) {
        this.tasks.add(task);
    }
    
    public Task remove(int serialNo) {
        return this.tasks.remove(serialNo - 1);
    }
    
    public Task markDone(int serialNo) {
        Task task = tasks.get(serialNo - 1);
        task.markAsDone();
        return task;
    }
    
    public List<Task> getTasks() {
        return tasks;
    }
    
    public int getTaskCount() {
        return tasks.size();
    }
}
