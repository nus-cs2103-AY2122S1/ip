import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static TaskList MainList = null;
    private ArrayList<String> taskList = new ArrayList<>();

    private TaskList() {}

    public static TaskList getTaskList() {
        if( MainList == null) {
            MainList = new TaskList();
        }

        return MainList;
    }

    public List<? extends String> getTasks() {
        if(MainList == null) {
            MainList = new TaskList();
        }

        return this.taskList;
    }
    public List<? extends String> addTask(String task) {
        if( MainList == null) {
            MainList = new TaskList();
        }

        this.taskList.add(task);
        return this.taskList;
    }
}
