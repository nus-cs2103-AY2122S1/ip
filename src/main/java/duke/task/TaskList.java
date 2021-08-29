package duke.task;
import java.util.ArrayList;

/**
 * A class for the list of tasks
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tl) {
        taskList = tl;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * A method to add a task to the list of tasks
     * @param task the task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * A method to delete a task to the list of tasks
     * @param index the index of the task to be deleted
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * A method to return the list of tasks
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * A method to return the size of the task list
     * @return size of task list
     */
    public static int getSize() {
        return taskList.size();
    }

    /**
     * A method to get the task from the task list based on the
     * given index
     * @param index index of task list to be retreived
     * @return Task that corresponds to the index.
     */
    public static Task get(int index) {
        return taskList.get(index);
    }
}
