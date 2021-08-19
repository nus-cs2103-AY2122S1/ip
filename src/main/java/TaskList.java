import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static TaskList MainList = null;
    private ArrayList<Task> taskList = new ArrayList<>();

    private TaskList() {}

    public static TaskList getTaskList() {
        if( MainList == null) {
            MainList = new TaskList();
        }

        return MainList;
    }

    public List<? extends Task> getTasks() {
        if(MainList == null) {
            MainList = new TaskList();
        }

        return this.taskList;
    }
    public List<? extends Task> addTask(String task) {
        if( MainList == null) {
            MainList = new TaskList();
        }
        Task newTask = new Task(task);
        this.taskList.add(newTask);
        return this.taskList;
    }
}
