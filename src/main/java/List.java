import java.util.ArrayList;

public class List {
    ArrayList<Task> list = new ArrayList<>(100);

    public List() {}

    public String add(Task task) {
        list.add(task);
        return task.description;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public String complete(int taskNo) {
        return list.get(taskNo).check();
    }
}
