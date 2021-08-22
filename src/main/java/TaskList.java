import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int taskNum) {
        return this.taskList.remove(taskNum);
    }

    public int taskListLen() {
        return this.taskList.size();
    }

}
