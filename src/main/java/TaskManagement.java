import java.util.List;
import java.util.ArrayList;

public class TaskManagement {
    private final ArrayList<String> tasks;

    public TaskManagement() {
        this.tasks = new ArrayList<String>();
    }

    public String addTask(String task) {
        this.tasks.add(task);
        return task;
    }

    public ArrayList<String> getTasks() {
        ArrayList<String> result = new ArrayList<>(this.tasks);
        return result;
    }

    public void showTasks() {
        System.out.println(Duke.HORIZONTAL_LINE);
        if (tasks.isEmpty()) {
            System.out.println(Duke.INDENTATION + "No tasks");
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.println(Duke.INDENTATION + (i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(Duke.HORIZONTAL_LINE);
    }
}
