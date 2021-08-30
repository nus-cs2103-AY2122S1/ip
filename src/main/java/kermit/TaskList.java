package kermit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kermit.tasks.Task;

/**
 *  Task list stores tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Task list constructor.
     * Initialises with no tasks.
     */
    public TaskList() {
        super();
    }

    /**
     * Task list constructor.
     * Initialises list with tasks.
     *
     * @param taskArr Tasks to initialise task list.
     */
    public TaskList(List<Task> taskArr) {
        super(taskArr);
    }

    /**
     * Mark task as complete using its index.
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
     * Delete task from task list using its index.
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
     * @param searchString String to search for.
     * @return ArrayList of all tasks that contain searchstring.
     */
    public TaskList filter(String searchString) {
        TaskList filteredTasks = new TaskList();
        Iterator<Task> taskIter = super.iterator();
        while (taskIter.hasNext()) {
            Task task = taskIter.next();
            if (task.getDescription().contains(searchString)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    /**
     * String representation of task list.
     *
     * @return String representation of task list.
     */
    @Override
    public String toString() {
        String stringifiedList = "";
        for (int i = 0; i < super.size(); i++) {
            stringifiedList += (i + 1) + ". " + super.get(i).toString();
            // After last item should not have line break
            if (i < super.size() - 1) {
                stringifiedList += "\n";
            }
        }
        return stringifiedList;
    }
}
