import java.util.ArrayList;

    public class TaskList {

    protected ArrayList<Task> allTasks;

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public void addTask(Task t) {
        allTasks.add(t);
    }

    public void removeTask(Task t) {
        allTasks.remove(t);
    }

    public Task getTask(int index) {
        return allTasks.get(index);
    }

    public int size() {
        return allTasks.size();
    }
}
