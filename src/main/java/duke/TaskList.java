package duke;

import duke.tasks.Task;
import java.util.List;

/**
 * Container for a list of task items.
 */
public class TaskList {
    private List<Task> items;

    /**
     * Instantiates a task list object.
     * @param items list of items to be stored in the task list.
     */
    public TaskList(List<Task> items) {
        this.items = items;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index index of the task specified.
     * @return task at the specified index.
     */
    public Task getTask(int index) {
        return items.get(index);
    }

    /**
     * Returns the list of task items.
     *
     * @return list of task items.
     */
    public List<Task> getItems() {
        return items;
    }

    /**
     * Return the number of task items in the list.
     *
     * @return number of task items in the list.
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Marks the specified task as completed.
     *
     * @param index index of the task that has been completed.
     */
    public void markTaskDone(int index) {
        items.get(index).markAsDone();
    }

    /**
     * Adds the specified task to the task list.
     *
     * @param task task to be added to the list.
     */
    public void addTask(Task task) {
        this.items.add(task);
    }

    /**
     * Deletes the specified task from the task list.
     *
     * @param index index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.items.remove(index);
    }

    /**
     * Prints the list of tasks the user has currently.
     */
    public void printList() {
        if (items.size() > 0) {
            System.out.println("Here are the tasks in your list:");

            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%d. " + items.get(i) + "\n", i + 1);
            }
        } else {
            System.out.println("You have no tasks in your list.");
        }

    }
}
