import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String add(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n  " + task + '\n' + "You have " + numTasks() + " tasks in the list\n";
    }

    public String markTaskDone(int i) {
        Task task = tasks.get(i-1);
        task.markDone();
        return "Nice! this task has been marked done:\n  " + task + "\n";
    }

    public String list() {
        String taskStringRepresentation = "";
        for(int i = 0; i < numTasks(); i++) {
           taskStringRepresentation += (i+1) + ". " + tasks.get(i) + "\n";
       }
       return "Here are the tasks in your list:\n" + taskStringRepresentation;
    }

    private int numTasks() {
        return tasks.size();
    }
}
