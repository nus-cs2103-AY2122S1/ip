package duke.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.data.exception.InvalidIndexException;
import duke.data.task.Task;

/**
 * Encapsulates the task list and its operations
 */
public class TaskList {
    /** List of tasks */
    protected ArrayList<Task> tasks;
    /** Number of tasks */
    protected int count;

    /**
     * Constructor for TaskList
     *
     * @param tasks the list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.count = tasks.size();
    }

    /**
     * Adds the given task into task list
     *
     * @param t the task to add
     * @return the updated list of tasks
     */
    public ArrayList<Task> add(Task t) {
        this.tasks.add(t);
        this.count++;
        return this.tasks;
    }

    /**
     * Deletes the task at given index from the task list
     *
     * @param index the index of the task
     * @return the updated list of tasks
     * @throws InvalidIndexException if given index is out of bounds
     */
    public ArrayList<Task> delete(int index) throws InvalidIndexException {
        if (isOutOfBounds(index)) {
            throw new InvalidIndexException();
        }
        this.tasks.remove(index);
        this.count--;
        return this.tasks;
    }

    /**
     * Retrieves the task in the given index
     *
     * @param index the index of the task to get
     * @return the task at the index
     * @throws InvalidIndexException if given index is out of bounds
     */
    public Task get(int index) throws InvalidIndexException {
        if (isOutOfBounds(index)) {
            throw new InvalidIndexException();
        }
        return this.tasks.get(index);
    }

    /**
     * Outputs the list of items in numbered format
     *
     * @return string representation of all the tasks in the list
     */
    public String printList () {
        return this.tasks.stream()
                .map(task -> String.format("\n%d. %s", tasks.indexOf(task) + 1, task.toString()))
                .reduce("", String::concat);
    }

    /**
     * Marks task at given index as done
     *
     * @param index index of task to be marked done
     * @return the updated list of tasks
     * @throws InvalidIndexException if given index is out of bounds
     */
    public ArrayList<Task> markDone(int index) throws InvalidIndexException {
        if (isOutOfBounds(index)) {
            throw new InvalidIndexException();
        }
        this.tasks.get(index).setDone();
        return this.tasks;
    }

    /**
     * Returns a list of tasks that satisfy the given predicate
     *
     * @param predicate the predicate
     * @return ArrayList that satisfy given predicate
     */
    public TaskList filter(Predicate<? super Task> predicate) {
        List<Task> filteredList = this.tasks.stream().filter(predicate).collect(Collectors.toList());
        return new TaskList(new ArrayList<>(filteredList));
    }

    /**
     * Checks if given index is out of bounds of this task list
     *
     * @param index the index to access in the list
     * @return true if index falls out of bounds of this task list, false otherwise
     */
    private boolean isOutOfBounds(int index) {
        return index >= this.count || index < 0;
    }

    public int getCount() {
        return this.count;
    }

    /**
     * Checks if given task is already in the list
     *
     * @param t the task to check for
     * @return true if it exists in the list, false if otherwise
     */
    public boolean contains(Task t) {
        return this.tasks.contains(t);
    }
}
