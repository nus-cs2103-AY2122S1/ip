import java.util.List;
import java.util.ArrayList;

public class TaskManagement {
    private final ArrayList<Task> tasks;

    public TaskManagement() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number: " + (index + 1) + " does not exist.");
        }
        return this.tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> result = new ArrayList<>(this.tasks);
        return result;
    }

    public int getSize() {
        return tasks.size();
    }

    public void showTasks() {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println(Duke.INDENTATION + "Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            System.out.println(Duke.INDENTATION + "No tasks");
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.println(Duke.INDENTATION + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    public void markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number: " + (index + 1) + " does not exist.");
        }
        Task temp = tasks.get(index);
        temp.markAsDone();
        tasks.set(index, temp);
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println(Duke.INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(Duke.INDENTATION + temp.toString());
        System.out.println(Duke.HORIZONTAL_LINE);
    }
}
