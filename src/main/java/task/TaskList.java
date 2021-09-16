package task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.ui.Ui;
import duke.util.Storage;

/**
 * Implementation of task list, which is a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructor, creates new task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor, takes in and uses existing task lists.
     *
     * @param tasks Existing task list to use.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null: "Tasks must be an arraylist, not null";
        this.tasks = tasks;
    }

    /**
     * Prints the number of tasks in the list.
     */
    public String getSize() {
        return Ui.messageListSize(tasks.size());
    }

    /**
     * Adds a task into the task list.
     *
     * @param task Task to add.
     */
    public String add(Task task) {
        if (task == null) {
            return Ui.MESSAGE_INVALID_ARG;
        }

        tasks.add(task);
        assert tasks.contains(task): "Task list should contain added task";
        Storage.saveList(tasks);
        return Ui.messageAddTask(task, getSize());

    }

    /**
     * Marks the task as complete (or incomplete if it is already complete).
     *
     * @param index Index of the task displayed by the list command.
     *              Actual index is (index - 1).
     */
    public String toggleDone(int index) {
        try {
            String result = tasks.get(index - 1).toggleDone();
            Storage.saveList(tasks);
            return result;

        } catch (IndexOutOfBoundsException e) {
            return Ui.messageNoTaskAtIndex(index);
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of the task displayed by the list command.
     *              Actual index is (index - 1).
     */
    public String delete(int index) {
        try {
            Task removedTask = tasks.remove(index - 1);
            assert !tasks.contains(removedTask): "Task list should not contain removed task";

            Storage.saveList(tasks);
            return Ui.messageRemoveTask(removedTask, getSize());
        } catch (IndexOutOfBoundsException e) {
            return Ui.messageNoTaskAtIndex(index);
        }
    }

    /**
     * Displays the list of tasks based on predicate filter.
     *
     * @param filters Predicates to filter; return true to display task.
     */
    public String displayList(ArrayList<Predicate<Task>> filters) {
        if (tasks.size() == 0) {
            return Ui.MESSAGE_NOTHING_TO_DISPLAY;
        }

        // Get resultant list after applying all filters
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> filters.stream()
                        .allMatch(predicate -> predicate.test(task)))
                .collect(Collectors.toList());

        if (filteredTasks.size() == 0) {
            return Ui.MESSAGE_NOTHING_TO_DISPLAY;
        }

        // Add proper formatting and return resultant list
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < filteredTasks.size(); i++) {
            result.append(i + 1).append(". ").append(filteredTasks.get(i)).append('\n');
        }

        return result.toString();
    }

    public void sort(ArrayList<Comparator<Task>> filters) {
        for (int i = filters.size() - 1; i >= 0; i--) {
            tasks.sort(filters.get(i));
        }
        Storage.saveList(tasks);
    }
}
