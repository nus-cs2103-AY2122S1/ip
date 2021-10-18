package duke.tasks;

import java.util.ArrayList;

/**
 * Encapsulates the information of a TaskList object which contains an ArrayList of Task objects.
 */
public class TaskList {
    private final ArrayList<Task> items;

    /**
     * Constructor that creates a TaskList object with the items properly initialised.
     */
    public TaskList() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        this.items.add(task);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param id The index of the task to be removed in the TaskList
     */
    public void deleteTask(int id) {
        this.items.remove(id);
    }

    /**
     * Marks a task as completed
     *
     * @param id The index of the task to be marked as completed in the TaskList
     */
    public void markTaskAsComplete(int id) {
        this.items.get(id).markAsComplete();
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return An int representing the number of tasks in the TaskList
     */
    public int getTaskCount() {
        return this.items.size();
    }

    /**
     * Returns the Task requested.
     *
     * @param id The index of the task to be retrieved from the TaskList
     * @return A Task.
     */
    public Task getTask(int id) {
        return this.items.get(id);
    }

    /**
     * Returns a TaskList containing all the tasks that contains the specified keyword.
     *
     * @param keyword The word used for the search.
     * @return A TaskList containing all the tasks that contains the specified keyword.
     */
    public TaskList searchTaskByKeyword(String keyword) {
        TaskList resultList = new TaskList();

        for (Task task : items) {
            String taskToStr = task.toString().toLowerCase();

            if (taskToStr.contains(keyword.trim().toLowerCase())) {
                resultList.addTask(task);
            }
        }

        return resultList;
    }

    /**
     * Returns true if taskList is empty.
     * Otherwise, returns false.
     *
     * @return A boolean indicating if the taskList is empty.
     */
    public boolean isListEmpty() {
        return items.size() == 0;
    }
}

