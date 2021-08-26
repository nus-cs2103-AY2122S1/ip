package duke.util;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import duke.date.Date;
import duke.exception.NoSuchTaskException;
import duke.task.DatedTask;
import duke.task.Task;

/** A wrapper class that holds the list of Tasks. */
public class TaskList {
    /** The actual list holding the Tasks. */
    private ArrayList<Task> list;

    /** Default TaskList constructor. */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * TaskList constructor. Used for constructing the list from storage.
     *
     * @param list The ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a Task to the list.
     *
     * @param task The Task object.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Returns the size of the list.
     *
     * @return Size of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the Task at the specified index in the list.
     *
     * @param index The index of Task to be obtained.
     * @return The Task at the specified index.
     * @throws NoSuchTaskException If the index exceeds the size of the list.
     */
    public Task getTask(int index) throws NoSuchTaskException {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    /**
     * Deletes the Task from the specified index and returns the deleted Task.
     *
     * @param index The index of Task to be deleted.
     * @return The Task deleted at the specified index.
     * @throws NoSuchTaskException If the index exceeds the size of the list.
     */
    public Task deleteTask(int index) throws NoSuchTaskException {
        try {
            return list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }

    }

    /**
     * Returns an array of string representation of the Tasks in the list, numbered from 1.
     *
     * @return An array of string representation of the Tasks in the list, numbered from 1.
     */
    public String[] toStringArray() {
        String[] copy = list.stream().map(Object::toString).toArray(String[]::new);
        for (int i = 0; i < size(); i++) {
            copy[i] = (i + 1) + "." + copy[i];
        }
        return copy;
    }

    /**
     * Returns an array of string representation of the Task in the list that matches the date given, numbered from 1.
     *
     * @param date The date to be queried.
     * @return An array of string representation of the Task in the list that matches the date given, numbered from 1.
     */
    public String[] toStringArray(Date date) {
        return list.stream()
                .filter((task) -> task instanceof DatedTask)
                .filter((task) -> ((DatedTask) task).getDate().equals(date))
                .map(Object::toString)
                .toArray(String[]::new);
    }

    /**
     * Performs an action on each Task in the list.
     *
     * @param consumer The action to be performed on the Task objects in the list.
     */
    public void forEach(Consumer<Task> consumer) {
        for (int i = 0; i < size(); i++) {
            consumer.accept(list.get(i));
        }
    }

    /**
     * Returns a TaskList containing Task that satisfy the supplied predicate.
     *
     * @param predicate The predicate to test the Task with.
     * @return A TaskList containing Task that satisfies the given predicate.
     */
    public TaskList filter(Function<Task, Boolean> predicate) {
        ArrayList<Task> res = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (predicate.apply(list.get(i))) {
                res.add(list.get(i));
            }
        }
        return new TaskList(res);
    }
}
