import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Storage storage = new Storage();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
        storage.save(tasks);
    }

    public String markDone(int i) {
        Task t = getTask(i);
        t.markDone();
        storage.save(tasks);
        return t.toString();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String removeTask(int i) {
        Task t = getTask(i);
        tasks.remove(i);
        storage.save(tasks);
        return t.toString();
    }
}
