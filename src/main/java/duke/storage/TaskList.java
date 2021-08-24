package duke.storage;

import duke.task.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
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

    /**
     * Finds the list of tasks matching a query.
     * @param query The query to match against.
     * @return The list of tasks matching the query.
     */
    public List<Task> findMatchingTasks(String query) {
        return tasks.stream().filter(task -> task.getDescription().contains(query)).collect(Collectors.toList());
    }
    
    public int getTaskCount() {
        return tasks.size();
    }
}
