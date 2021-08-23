import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    public static final int MAX_TASKS = 100;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }

    public Task deleteTask(int taskNum) {
        return this.taskList.remove(taskNum);
    }

    public int taskListLen() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        int taskListLen = this.taskList.size();
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskListLen; i++) {
            int currTaskNum = i + 1;
            msg.append("\n").append(currTaskNum).append(". ").append(this.taskList.get(i).toString());
        }
        return msg.toString();
    }
}
