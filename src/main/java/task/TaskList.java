package task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.Storage;
import duke.Ui;

/**
 * Implementation of task list, which is a list of tasks
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructor, creates new task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor, takes in and uses existing task lists
     *
     * @param tasks Existing task list to use
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints the number of tasks in the list
     */
    public String getSize() {
        return tasks.size() == 1
                ? "There is 1 task in your list"
                : "There are " + tasks.size() + " tasks in your list";
    }

    /**
     * Adds a task into the task list
     *
     * @param task Task to add
     */
    public String add(Task task) {
        if (task != null) {
            tasks.add(task);
            Storage.saveList(tasks);
            return "Got it. I've added this task:\n" + Ui.OUTPUT_SPACES + task + '\n' + getSize();
        } else {
            return "Invalid task!";
        }

    }

    /**
     * Marks the task as complete (or incomplete if it is already complete)
     *
     * @param index Index of the task displayed by the list command
     *              Actual index is (index - 1)
     */
    public String toggleDone(int index) {
        try {
            boolean result = tasks.get(index - 1).toggleDone();
            Storage.saveList(tasks);
            return (result
                    ? "sugoi! Duke-san marked this task as done!"
                    : "Duke-san marked this task as not done!")
                    + '\n' + Ui.OUTPUT_SPACES + tasks.get(index - 1);

        } catch (IndexOutOfBoundsException e) {
            return "There's no task at index " + index + "!!";
        }
    }

    /**
     * Deletes a task from the task list
     *
     * @param index Index of the task displayed by the list command
     *              Actual index is (index - 1)
     */
    public String delete(int index) {
        try {
            Task removed = tasks.remove(index - 1);
            Storage.saveList(tasks);
            return "Noted. Duke-san removed this task:"
                    + removed
                    + getSize();
        } catch (IndexOutOfBoundsException e) {
            return "There's no task at index " + index + "!!";
        }
    }

    /**
     * Displays the list of tasks based on predicate filter
     *
     * @param filters Predicates to filter; return true to display task
     */
    public String displayList(ArrayList<Predicate<Task>> filters) {
        if (tasks.size() == 0) {
            return "There is nothing to display! :angery:";
        } else {
            // Get resultant list after applying all filters
            List<Task> list = tasks.stream()
                    .filter(task -> filters.stream()
                            .allMatch(predicate -> predicate.test(task)))
                    .collect(Collectors.toList());

            if (list.size() == 0) {
                return "There is nothing to display! :angery:";
            } else {
                // Add proper formatting and display list
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    result.append(i + 1).append(". ").append(list.get(i)).append('\n');
                }

                return result.toString();
            }
        }
    }
}
