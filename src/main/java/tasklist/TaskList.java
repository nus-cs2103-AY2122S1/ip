package tasklist;

import java.io.Serializable;
import java.util.ArrayList;

import exception.DukeException;
import models.Task;

/**
 * TaskList class that contains all of the tasks that has been inputted by the user.
 */
public class TaskList implements Serializable {

    /** ArrayList that store all of the Task objects. */
    private final ArrayList<Task> list;

    /**
     * Constructor of the TaskList class where we initialize ArrayList which will contain all of the Tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Function to add task to the ArrayList.
     *
     * @param task Task object that will be added
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Toggle the isDone boolean inside Task objects.
     *
     * @param index Index of the task which is going to be set done.
     * @throws DukeException If there is no task with the specified index.
     */
    public void setDone(int index) throws DukeException {
        try {
            this.list.get(index).setDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with number " + (index + 1) + " in the list");
        }
    }

    /**
     * Return task with the specified index from the ArrayList.
     *
     * @param index Index of the task that will be retrieved.
     * @return Task with the specified index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Return the last Task object in the ArrayList.
     *
     * @return Task with the last index.
     */
    public Task getLastTask() {
        return this.getTask(this.getSize() - 1);
    }

    /**
     * Return the size of the ArrayList.
     *
     * @return The number of Tasks in the ArrayList.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Delete the task with specified index and returning the String value of the deleted Task.
     *
     * @param index Index of the task that will be deleted.
     * @return String value of the deleted Task.
     * @throws DukeException If there is no task with the specified index.
     */
    public String deleteTask(int index) throws DukeException {
        try {
            return this.list.remove(index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with number " + (index + 1) + " in the list");
        }
    }

    /**
     * Function implementation that returns all of the tasks with the matched keyword in a TaskList.
     *
     * @param keyword Keyword that will be searched.
     * @return TaskList object with all lists that match the keyword.
     */
    public TaskList findKeyword(String keyword) {
        TaskList result = new TaskList();
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).toString().contains(keyword)) {
                result.addTask(this.list.get(i));
            }
        }
        return result;
    }

    /**
     * Return TaskList String representation.
     *
     * @return String value of the TaskList object.
     */
    @Override
    public String toString() {
        String result = "Here are the tasks in your list!\n";
        for (int i = 1; i < this.list.size() + 1; i++) {
            result += i + ". " + this.list.get(i - 1);
            if (i != this.list.size()) {
                result += "\n";
            }
        }
        return result;
    }
}
