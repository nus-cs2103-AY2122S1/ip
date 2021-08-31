package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import duke.tasks.Task;

/**
 * Container for a list of task items.
 */
public class TaskList {
    private List<Task> items;

    /**
     * Instantiates a task list object.
     * @param items List of items to be stored in the task list.
     */
    public TaskList(List<Task> items) {
        this.items = items;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task specified.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return items.get(index);
    }

    /**
     * Returns the list of task items.
     *
     * @return List of task items.
     */
    public List<Task> getItems() {
        return items;
    }

    /**
     * Returns the number of task items in the list.
     *
     * @return Number of task items in the list.
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Marks the specified task as completed.
     *
     * @param index Index of the task that has been completed.
     */
    public void markTaskDone(int index) {
        items.get(index).markAsDone();
    }

    /**
     * Adds the specified task to the task list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        this.items.add(task);
    }

    /**
     * Deletes the specified task from the task list.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.items.remove(index);
    }

    /**
     * Finds a task with the given description.
     *
     * @param desc Description of the task that the user wants to find.
     */
    public void findTask(String desc) {
        if (items.size() > 0) {
            List<Task> foundItems = new ArrayList<>();

            // Solution below adapted from:
            // https://www.w3schools.com/java/java_regex.asp
            Pattern pattern = Pattern.compile(desc, Pattern.CASE_INSENSITIVE);

            for (Task item : items) {
                if (pattern.matcher(item.toString()).find()) {
                    foundItems.add(item);
                }
            }

            if (foundItems.size() > 0) {
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < foundItems.size(); i++) {
                    System.out.printf("%d. " + foundItems.get(i) + "\n", i + 1);
                }
            } else {
                System.out.println("There is no task with the specified description!");
            }
        } else {
            System.out.println("You have no tasks in your list to find a matching task.");
        }
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
