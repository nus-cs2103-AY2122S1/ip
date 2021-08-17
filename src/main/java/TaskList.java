import java.util.ArrayList;

/**
 * A task list to keep track of the tasks.
 */
public class TaskList {
    /**
     * ArrayList of tasks that contains all the tasks to be completed.
     */
    private ArrayList<Task> tasks;

    /**
     * Length of the tasks available.
     */
    private int length = 0;

    /**
     * Empty TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * TaskList constructor with tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.length = tasks.size();
    }

    /**
     * Method to check if a task with the corresponding index is in the task list.
     *
     * @param index Index of task to be checked.
     * @return boolean Indicate whether the task is in the task list.
     */
    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < length;
    }

    /**
     * Method to return the specific task asked for.
     *
     * @param index Index of the task desired.
     * @return The task corresponding to the index given.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Method to add a new task to the task list.
     *
     * @param newTask The task to be added.
     * @return A new task list that contains the required tasks.
     */
    public TaskList add(Task newTask) {
        ArrayList<Task> newList = new ArrayList<>(tasks);
        newList.add(newTask);
        return new TaskList(newList);
    }

    /**
     * Method to mark a specific task as completed.
     *
     * @param index Index of the task to be marked as completed.
     * @return The task that was marked as completed.
     */
    public Task markTaskAsCompleted(int index) {
        Task task = tasks.get(index);
        task.markAsCompleted();
        return task;
    }

    /**
     * Method to delete task from the task list.
     *
     * @param index Index of the task to be deleted.
     * @return A new task list that contains the remaining tasks.
     */
    public TaskList deleteTask(int index) {
        ArrayList<Task> newList = new ArrayList<>(tasks);
        newList.remove(index);
        return new TaskList(newList);
    }

    /**
     * Gets the status of the current task list.
     *
     * @return String representation of the number of tasks in the task list.
     */
    public String status() {
        String t = this.length != 1 ? "tasks" : "task";
        return
                String.format("Now you have %d %s in the list.",
                this.length, t);
    }

    /**
     * String representation of the task list.
     * @return String representation of the respective tasks
     * in the task list.
     */
    @Override
    public String toString() {
        String str = "";
        int i = 1;
        for (Task item : tasks) {
            str += String.format("%4s%d. %s\n", " ", i, item);
            i++;
        }
        return str;
    }
}
