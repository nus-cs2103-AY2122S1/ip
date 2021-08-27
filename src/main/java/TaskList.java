import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    // add
    public void addTask(Task task) {
        list.add(task);
    }

    // delete
    public void deleteTask(Task task) {
        list.remove(task);
    }

    // getSize()
    public int getSize() {
        return list.size();
    }

    // getTask()
    public Task getTask(int i) {
        return list.get(i);
    }
}
