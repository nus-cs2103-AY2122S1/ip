package tasks;

import java.util.ArrayList;

/**
 * This TaskList class encapsulates the collection of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the existing task list.
     * @param task The task to be added into the list.
     * @return True
     */
    public boolean addTask(Task task) {
        tasks.add(task);
        return true;
    }

    /**
     * Finds a task specified by the keyword.
     * @param keyword The keyword used to find tasks.
     * @return The list of tasks that contains the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList output = new TaskList();
        for (Task t : this.tasks) {
            String taskString = t.toString().toLowerCase();
            boolean isKeywordFound = taskString.contains(keyword);
            if (isKeywordFound) {
                output.addTask(t);
            }
        }
        return output;
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Marks a task of the specified index as done.
     * @param taskNumber The index of the task to be marked as done.
     * @return True
     */
    public boolean markAsDone(int taskNumber) {
        tasks.get(taskNumber - 1).markDone();
        return true;
    }

    public void clear() {
        this.tasks.clear();
    }

    /**
     * Returns a task specified by the index.
     * @param taskNumber The index of the task to be retrieved.
     * @return The task specified by the index.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /**
     * Removes a task specified by the index.
     * @param taskNumber the index of the task to be removed.
     * @return The task specified by the index.
     */
    public boolean remove(int taskNumber) {
        tasks.remove(taskNumber - 1);
        return true;
    }

    /**
     * Returns true if the task list is empty.
     * @return True if the task list is empty; false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    @Override
    public String toString() {
        // Empty task list will print nothing
        StringBuilder listString = new StringBuilder();
        int numTasks = tasks.size();
        for (int i = 0; i < numTasks; i++) {
            listString.append(i + 1);
            listString.append(". ");
            listString.append(tasks.get(i).toString());
            if (i != numTasks - 1) {
                listString.append("\n");
            }
        }
        return listString.toString();
    }
}
