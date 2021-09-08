package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Manages the list of tasks for duke.Duke.
 */
public class TaskList {
    private final Storage storage;
    private final List<Task> tasks;

    /**
     * Constructs a TaskList given a Storage
     * @param storage Storage to use for storing and reading from file
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>(storage.readTasks());
    }

    /**
     * Adds a task to the list.
     * @param task Task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
        this.saveToStorage();
    }

    /**
     * Removes a task from the list
     * @param index Position of the task to be deleted
     * @return
     */
    public Task delete(int index) {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("There is no task " + index);
        }

        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        this.saveToStorage();
        return task;
    }

    /**
     * Marks a task in the list as completed.
     * @param index Position of task to be marked
     * @return Task that was deleted
     */
    public Task markAsDone(int index) {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("There is no task " + index);
        }

        Task task = tasks.get(index);
        task.complete();
        this.saveToStorage();
        return task;
    }

    /**
     * Returns the size of the TaskList.
     * @return Size of task list
     */
    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("There is no task " + index);
        }

        return tasks.get(index);
    }

    /**
     * Finds tasks with descriptions containing the prompt.
     * @param prompt Search string
     * @return List of results matching prompt
     */
    public List<FindResult> find(String prompt) {
        List<FindResult> results = new ArrayList<>();
        String promptLower = prompt.toLowerCase();
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(promptLower)) {
                results.add(new FindResult(task, i));
            }
        }
        return results;
    }

    public void saveToStorage() {
        storage.saveTasks(tasks);
    }

    /**
     * Sorts the tasks
     * @param order Order for tasks to be sorted in
     */
    public void sort(SortOrder order) {
        switch (order) {
        case ALPHABETICALLY:
            tasks.sort((Task t1, Task t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()));
            break;
        case CHRONOLOGICALLY:
            tasks.sort(Comparable::compareTo);
            break;
        default:
            throw new DukeException("No such sorting order!");
        }
        this.saveToStorage();
    }

    /**
     * Represents a found result.
     */
    public static class FindResult {
        /**
         * Task that was found.
         */
        public final Task task;

        /**
         * Index of the task in the full list.
         */
        public final int index;

        /**
         * Represents a result from a search.
         * @param task Task that matches the query
         * @param index Position of the task in the list
         */
        public FindResult(Task task, int index) {
            this.task = task;
            this.index = index;
        }
    }
}
