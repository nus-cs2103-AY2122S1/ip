import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String add(String taskDescription) {
        Task task = new Task(taskDescription);
        tasks.add(task);
        return "added task: " + task;
    }

    public String markTaskDone(int i) {
        Task task = tasks.get(i-1);
        task.markDone();
        return "Nice! this task has been marked done: " + task;
    }

    public String[] list() {
       String[] taskStringRepresentations = new String[tasks.size()];
       for(int i = 0; i < taskStringRepresentations.length; i++) {
           taskStringRepresentations[i] = (i+1) + ". " + tasks.get(i);
       }
       return taskStringRepresentations;
    }
}
