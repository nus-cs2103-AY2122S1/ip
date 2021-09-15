import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> savedData) {
        this.list = savedData;
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int number) {
        list.remove(number);
    }

    public void done(int number) {
        list.get(number).markAsDone();
    }
}
