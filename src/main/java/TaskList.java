import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> t) {
        this.taskList = t;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
}
