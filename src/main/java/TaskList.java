import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTaskAsDone(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("markTaskAsDone index out of range.");
        }
        Task task = tasks.get(i);
        task.markAsDone();
        return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }
}
