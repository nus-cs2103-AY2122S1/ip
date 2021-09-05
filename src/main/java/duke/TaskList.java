package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
     * Finds a list of tasks with the given description.
     *
     * @param desc Description of the task that the user wants to find.
     * @return The task with the specified description.
     */
    public String findTask(String desc) {
        if (items.size() == 0) {
            return "You have no tasks in your list to find a matching task.";
        }

        List<Task> foundItems = new ArrayList<>();

        // Solution below adapted from:
        // https://www.w3schools.com/java/java_regex.asp
        Pattern pattern = Pattern.compile(desc, Pattern.CASE_INSENSITIVE);

        for (Task item : items) {
            if (pattern.matcher(item.getDescription()).find()) {
                foundItems.add(item);
            }
        }

        if (foundItems.size() == 0) {
            return "There is no task with the specified description!";
        }

        StringBuilder foundListStr = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < foundItems.size(); i++) {
            foundListStr.append(i + 1).append(". ").append(foundItems.get(i)).append("\n");
        }
        return foundListStr.toString();
    }

    /**
     * Displays a list of tasks that matches the given date.
     *
     * @param date Date to find matching tasks.
     * @return String representation of a list of tasks to be scheduled at the given date.
     */
    public String viewSchedule(LocalDate date) {
        if (items.size() == 0) {
            return "You have no tasks in your list.";
        }

        List<Task> scheduledTasks = new ArrayList<>();

        for (Task item : items) {
            if (item.getDate().toLocalDate().equals(date)) {
                scheduledTasks.add(item);
            }
        }

        if (scheduledTasks.size() == 0) {
            return "There is no scheduled tasks on the specified date!";
        }

        StringBuilder scheduledTasksStr = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < scheduledTasks.size(); i++) {
            scheduledTasksStr.append(i + 1).append(". ").append(scheduledTasks.get(i)).append("\n");
        }
        return scheduledTasksStr.toString();
    }

    /**
     * Sorts the list of tasks in ascending date order.
     *
     * @return String representation of the list of sorted tasks.
     */
    public String sortList() {
        if (items.size() == 0) {
            return "You have no tasks in your list to sort.";
        }

        items.sort(Comparator.comparing(Task::getDate));
        return printList();
    }

    /**
     * Prints the list of tasks the user has currently.
     *
     * @return The list of tasks the user has.
     */
    public String printList() {
        if (items.size() > 0) {
            StringBuilder temp = new StringBuilder("Here are the tasks in your list:\n");

            for (int i = 0; i < items.size(); i++) {
                temp.append(i + 1).append(". ").append(items.get(i)).append("\n");
            }
            return temp.toString();
        } else {
            return "You have no tasks in your list.";
        }
    }
}
