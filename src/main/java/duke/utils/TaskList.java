package duke.utils;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Comparator;

/** TaskList that handles the Tasks in Duke */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * A constructor for a TaskList
     *
     * @param taskList An ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * @return Whether the taskList has no tasks
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * @return The number of tasks in the taskList
     */
    public int numberOfTasks() {
        return taskList.size();
    }

    /**
     * Adds a Task into the TaskList
     *
     * @param task The Task to be added into the TaskList
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Gets a Task from the TaskList
     *
     * @param taskIndex The Index of the Task in the TaskList
     * @return The Task at the index given
     */
    public Task getTask(int taskIndex){
        return taskList.get(taskIndex);
    }

    /**
     * Deletes a Task from the TaskList
     *
     * @param deleteIndex The index of the Task to be deleted
     */
    public void removeTask (int deleteIndex) {
        taskList.remove(deleteIndex);
    }

    /**
     * Searches for a task in the taskList
     * @param searchQuery A Query String
     * @return Tasks that have searchQuery as a substring
     */
    public TaskList searchTasks(String searchQuery) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(searchQuery)) {
                res.add(task);
            }
        }
        return new TaskList(res);
    }

    /**
     * Sorts the TaskList
     * @param comparator The comparator used to compare two Tasks
     * @return A sorted TaskList sorted using the comparator
     */
    public TaskList sortTasks(Comparator<? super Task> comparator) {
        ArrayList<Task> sortedTasks = new ArrayList<>(taskList);
        sortedTasks.sort(comparator);
        return new TaskList(sortedTasks);
    }
}
