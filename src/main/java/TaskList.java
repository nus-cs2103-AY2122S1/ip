import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> task;

    public TaskList() {
        this.task = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> task) {
        this.task = task;
    }

    public void add(Task t) {
        task.add(t);
    }

    public Task remove(int i) {
        return task.remove(i);
    }

    public void markDone(int i) {
        task.get(i).markDone();
    }

    public Task get(int i) {
        return task.get(i);
    }

    public int size() {
        return task.size();
    }

    public ArrayList<Task> getTasks() {
        return this.task;
    }
}