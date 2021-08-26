import java.util.ArrayList;

public class TaskList {


    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = null;
    }

    public TaskList(ArrayList<Task> taskList) {

        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int num) {

        taskList.remove(num-1);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

}
