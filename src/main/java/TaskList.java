import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArr = new ArrayList<>();
    public TaskList(ArrayList<Task> tasks) {
        taskArr = tasks;
    }

    public void add(Task t) {
        taskArr.add(t);
    }

    public Task get(int i) {
        return taskArr.get(i);
    }

    public Task remove(int i) {
        return taskArr.remove(i);
    }

    public int getSize() {
        return taskArr.size();
    }
}
