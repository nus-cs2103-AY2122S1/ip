import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public Task deleteTask(int index) {
        Task taskToDelete = taskList.get(index);
        taskList.remove(index);
        return taskToDelete;
    }

    public Task completeTask(int index) {
        Task taskToMarkAsComplete = taskList.get(index);
        taskToMarkAsComplete.setDone(true);
        return taskToMarkAsComplete;
    }
}
