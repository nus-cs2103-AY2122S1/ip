import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String add(String taskDescription) {
        tasks.add(new Task(taskDescription));
        return "added task: " + taskDescription;
    }

    public String[] list() {
       String[] taskStringRepresentations = new String[tasks.size()];
       for(int i = 0; i < taskStringRepresentations.length; i++) {
           taskStringRepresentations[i] = (i+1) + ". " + formatTask(tasks.get(i));
       }
       return taskStringRepresentations;
    }

    private String formatTask(Task task) {
        return task.getDescription();
    }
}
