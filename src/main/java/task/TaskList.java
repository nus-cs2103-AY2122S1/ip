package task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public String list() {
        String message = "Here are the tasks in your list:\n";
        int count = 1;
        for (Task t : taskList) {
            message = message + Integer.toString(count) + ". "
                    + t.toString() + "\n";
            count++;
        }
        return message;
    }

    public void done(int index) {
        taskList.get(index).setDone();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public Task get(int index) {
        Task task = taskList.get(index);
        return task;
    }
}
