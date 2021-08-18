import java.util.ArrayList;

public class List {
    ArrayList<Task> list = new ArrayList<>(100);

    public List() {}

    public String add(Task task) {
        list.add(task);
        return task.taskName;
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
