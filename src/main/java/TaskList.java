import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contains task list and executes operations on task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int pos) {
        this.tasks.remove(pos - 1);
    }

    public ArrayList<Task> checkDate(LocalDate date) {
        ArrayList<Task> results = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.by.equals(date)) {
                    results.add(t);
                }
            }

            if (t instanceof Event) {
                Event e = (Event) t;
                if (e.at.equals(date)) {
                    results.add(t);
                }
            }
        }

        return results;
    }
}
