package tasklist;

import java.io.Serializable;
import java.util.ArrayList;

import exception.DukeException;
import models.Task;

/**
 * TaskList class that contains all of the tasks that has been inputted by the user.
 */
public class TaskList implements Serializable {

    private TaskList previous;

    private TaskList next;

    /** ArrayList that store all of the Task objects. */
    private final ArrayList<Task> list;

    /**
     * Constructor of the TaskList class where we initialize ArrayList which will contain all of the Tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        previous = null;
        next = null;
    }

    /**
     * Function to add task to the ArrayList.
     *
     * @param task Task object that will be added.
     */
    public void addTask(Task task) {
        assert task != null;
        next = copyTaskList();
        next.list.add(task);
        next.previous = this;
    }

    /**
     * Toggle the isDone boolean inside Task objects.
     *
     * @param index Index of the task which is going to be set done.
     * @throws DukeException If there is no task with the specified index.
     */
    public void setDone(int index) throws DukeException {
        try {
            if (index < 0 || index >= list.size()) {
                throw new IndexOutOfBoundsException();
            }
            next = copyTaskList();
            next.list.get(index).setDone();
            next.previous = this;
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
            if (index < 0 || index >= list.size()) {
                throw new IndexOutOfBoundsException();
            }
            next = copyTaskList();
            String result = next.list.remove(index).toString();
            next.previous = this;
            return result;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with number " + (index + 1) + " in the list");
        }
    }

    /**
     * Function implementation that returns all of the tasks with the matched keyword in a TaskList.
     *
     * @param keywords Keywords that will be searched.
     * @return TaskList object with all lists that match the keyword.
     */
    public TaskList findKeyword(String ... keywords) {
        TaskList result = new TaskList(new ArrayList<>());
        for (String keyword: keywords) {
            for (int i = 0; i < this.list.size(); i++) {
                if (this.list.get(i).toString().contains(keyword)) {
                    result.addTask(this.list.get(i));
                }
            }
        }
        return result;
    }


    /**
     * Returns an exact copy of the current TaskList.
     *
     * @return A copy of the current TaskList.
     */
    public TaskList copyTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task: list) {
            tasks.add(task.copyTask());
        }
        return new TaskList(tasks);
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

    /**
     * Gets the previous state of the TaskList.
     *
     * @return The previous state of the TaskList object.
     */
    public TaskList getPrevious() {
        return previous;
    }

    /**
     * Gets the next state of the TaskList.
     *
     * @return The next state of the TaskList object.
     */
    public TaskList getNext() {
        return next;
    }
}
