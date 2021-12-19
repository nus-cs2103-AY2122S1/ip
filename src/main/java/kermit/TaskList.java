package kermit;

import java.util.ArrayList;
import java.util.List;

import kermit.tasks.Task;

/**
 *  Task list stores tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructs TaskList.
     * Initialises with no tasks.
     */
    public TaskList() {
        super();
    }

    /**
     * Constructs TaskList.
     * Initialises list with tasks.
     *
     * @param taskArr Tasks to initialise task list.
     */
    public TaskList(List<Task> taskArr) {
        super(taskArr);
    }

    /**
     * Marks task as complete using its index.
     * Index is zero indexed.
     *
     * @param index index of task to mark as complete.
     * @return Task that has been marked as complete.
     */
    public Task markTaskAsComplete(int index) {
        Task task = super.get(index);
        task.markAsComplete();
        return task;
    }

    /**
     * Deletes task from task list using its index.
     * List is zero-indexed.
     *
     * @param index Index of task to delete.
     * @return Task that is deleted.
     */
    public Task deleteTask(int index) {
        Task task = super.get(index);
        super.remove(index);
        return task;
    }

    /**
     * Filters tasks that contain a string.
     *
     * Credits to baeldung for use of filter stream which has been modified
     * https://www.baeldung.com/java-stream-filter-lambda
     *
     * @param searchString String to search for.
     * @return ArrayList of all tasks that contain searchString.
     */
    public TaskList filter(String searchString) {
        TaskList filteredTasks = new TaskList();

        super.stream()
                .filter(task -> task.getDescription().contains(searchString))
                .forEach(task -> filteredTasks.add(task));

        return filteredTasks;
    }

    /**
     * Returns string representation of task list.
     *
     * @return String representation of task list.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String listItem;
        for (int i = 0; i < super.size(); i++) {
            listItem = (i + 1) + ". " + super.get(i).toString();
            stringBuilder.append(listItem);
            // After last item should not have line break
            if (i < super.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
