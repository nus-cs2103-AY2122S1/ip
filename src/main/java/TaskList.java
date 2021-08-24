import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.list = taskList;
    }

    public Task markAsDone(int taskNumber) {
        Task task = this.list.get(taskNumber);
        task.markAsDone();
        return task;
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public Task remove(int taskNumber) {
        Task task = this.list.get(taskNumber);
        this.list.remove(task);
        return task;
    }

    public int size() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        return "Now you have " + list.size() + " tasks in the list.";
    }
}
