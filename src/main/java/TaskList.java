import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
}
