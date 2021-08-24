import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> storage) {
        this.tasks = storage;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public Task removeTask(int i) {
        return this.tasks.remove(i);
    }

    public int numOfTasks() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String list = "";

        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            int taskNum = i + 1;
            list = list + "\n" + taskNum + "." + curr.toString();
        }

        return list;
    }
}
