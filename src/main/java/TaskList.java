import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public void printList() {
        int ctr = 1;
        for (Task task: taskList) {
            System.out.println("\t" + ctr + "." + taskList.get(ctr - 1));
            ctr++;
        }
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
