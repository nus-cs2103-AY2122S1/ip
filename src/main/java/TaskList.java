import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public String getList() {
        String result = "";
        for (int index = 0; index < this.taskList.size(); index++) {
            result = result + (index + 1) + ". " + this.taskList.get(index).getTask() + "\n";
        }
        return result;
    }
}
