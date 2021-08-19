import java.util.ArrayList;
import java.util.List;


public class TaskList {
    protected List<Task> tasklist;

    public TaskList(ArrayList tasklist) {
        this.tasklist = tasklist;
    }

    public void addTask(Task task) {
        this.tasklist.add(task);
    }

    public String addTaskToString(Task task) {
        return ("Got it. I've added this task: \n" +
                task.toString()
                + "\nNow you have " + this.tasklist.size() + " tasks in the list.");
    }

    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    public int getSize() {
        return this.tasklist.size();
    }
}
