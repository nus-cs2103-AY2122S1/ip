package duke.task;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tl) {
        taskList = tl;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public static void doneTask(int index) {
        taskList.get(index).markAsDone();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static int getSize() {
        return taskList.size();
    }

    public static Task get(int index) {
        return taskList.get(index);
    }
}
