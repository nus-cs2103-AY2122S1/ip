import java.util.ArrayList;

/**
 * Contains the task list.
 *
 * @author Adam Ho
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

}
