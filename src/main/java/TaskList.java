import java.util.ArrayList;
class TaskList {
    private ArrayList<Task> taskList;

    TaskList(ArrayList<Task> list) {
        taskList = list;
    }

    protected int length() {
        return taskList.size();
    }

    protected void addTask(Task task) {
        taskList.add(task);
    }

    protected Task getTask(int index) {
        return taskList.get(index);
    }

    protected void deleteTask(int index) {
        taskList.remove(index);
    }

    protected ArrayList<Task> toArrayList() {
        return taskList;
    }
}
