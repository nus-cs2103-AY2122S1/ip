import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public int getListSize() {
        return taskList.size();
    }

    public String getTaskString(int index) {
        return taskList.get(index).toString();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
