package fan.cs2103t.duke.task;

import java.util.ArrayList;

/**
 * Represents a list of task.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the specified list.
     *
     * @param list the list to be the main list of the task list.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Appends the specified task to the end of this task list.
     *
     * @param task task to be appended to this task list.
     * @return <code>true</code> if the task is added successfully.
     */
    public boolean addTask(Task task) {
        try {
            tasks.add(task);
        } catch (Exception e) {
            System.err.println("Fail to add task: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Removes the task at the specified position in this task list.
     * Shifts any subsequent tasks to the left (subtracts one from their indices).
     *
     * @param taskIndex the index of the task to be removed.
     * @return <code>true</code> if the task is deleted successfully.
     */
    public boolean deleteTask(int taskIndex) {
        try {
            tasks.remove(taskIndex);
        } catch (Exception e) {
            System.err.println("Fail to delete task: " + e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in this task list.
     *
     * @return the number of tasks in this task list.
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }

    /**
     * Returns the string representation of this task list.
     *
     * @return the string representation of this task list.
     */
    public String printList() {
        StringBuilder listAsString = new StringBuilder();
        int count = 0;
        for (Task task : tasks) {
            count++;
            listAsString.append(count)
                    .append(".")
                    .append(task.getDescriptionWithStatus())
                    .append("\n");
        }
        if (count > 0) {
            listAsString.setLength(listAsString.length() - 1);
        }
        return listAsString.toString();
    }

}
