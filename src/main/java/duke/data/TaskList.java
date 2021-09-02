package duke.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.tasks.Task;

/**
 * An encapsulation of a list of tasks handled by our program.
 */
public class TaskList {
    protected List<Task> taskData;

    /**
     * Initialises the tasklist with an empty arraylist.
     */
    public TaskList() {
        this.taskData = new ArrayList<>();
    }

    /**
     * Initialises the tasklist with an existing list of tasks.
     *
     * @param taskData A list of tasks to be kept by the tasklist
     */
    public TaskList(List<Task> taskData) {
        this.taskData = taskData;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list
     */
    public void add(Task task) {
        this.taskData.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index The index of the task to be removed
     * @return The Task that was removed
     */
    public Task remove(int index) {
        return this.taskData.remove(index);
    }

    /**
     * Returns a task from the list at the given index.
     *
     * @param index The index of the task to be retrieved
     * @return The task at the given index
     */
    public Task get(int index) {
        return this.taskData.get(index);
    }

    /**
     * Returns true iff the list has no tasks
     *
     * @return Whether the list does not have any tasks
     */
    public boolean isEmpty() {
        return this.taskData.isEmpty();
    }

    /**
     * Returns the number of tasks held by this list
     *
     * @return An integer representing the size of the list of tasks held
     */
    public int size() {
        return this.taskData.size();
    }

    /**
     * Returns a new tasklist containing only the tasks that have a due date on the same date as the given date
     *
     * @param queryDate The date that we want to retrieve tasks of
     * @return A new tasklist containing only tasks due on the given date
     */
    public TaskList filterByDate(LocalDate queryDate) {
        return new TaskList(this.taskData.stream().filter(x -> x.isDue(queryDate)).collect(Collectors.toList()));
    }

    /**
     * Returns a new TaskList containing Tasks whose description contains the given keyword/String
     *
     * @param keyword A String to be used to filter Tasks with descriptions
     * @return A new TaskList of smaller/equal size
     */
    public TaskList containsKeyword(String keyword) {
        return new TaskList(
                this.taskData.stream().filter(x -> x.containsKeyword(keyword)).collect(Collectors.toList()));
    }

    /**
     * Does a deep comparison of this object to another object.
     *
     * @param otherObj The other object to be compared to
     * @return Returns true iff the two objects are of same type and same value in every field
     */
    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof TaskList)) {
            return false;
        } else {
            final TaskList otherTaskList = (TaskList) otherObj;

            if (this.taskData == null && otherTaskList.taskData == null) {
                return true;
            } else if (this.taskData == null || otherTaskList.taskData == null) {
                return false;
            } else {
                return this.taskData.equals(otherTaskList.taskData);
            }
        }
    }
}
