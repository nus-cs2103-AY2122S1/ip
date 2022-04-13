package duke;

import java.time.DateTimeException;
import java.util.ArrayList;

/**
 * TaskList manages all access of the list of Tasks, namely reading, adding, updating and deleting
 */
public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates and adds a Task to the list from the given String representation
     * @param repr the String representation of the Task
     * @return true if task is successfully added, else false
     * @throws DukeException if the the String representation is invalid
     */
    public boolean addTaskFromRepr(String repr) throws DukeException.EmptyTaskDescriptionException, DukeException.InvalidReprException {
            Task t = Task.fromRepr(repr);
            return tasks.add(t);
    }

    /**
     * adds a Task to the list
     * @param t the Task to be added
     * @return true if the task is successfully added, else false
     */
    public boolean addTask(Task t) {
        return tasks.add(t);
    }


    /**
     * marks the task at 1-based index i as complete
     * @param i the 1-based index of the task to be marked as complete
     * @return the task that is marked as complete
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public Task markAsComplete(int i) throws IndexOutOfBoundsException {
        Task t = tasks.get(i - 1);
        t.markAsComplete();
        return t;
    }

    /**
     * deletes the task at 1-based index i
     * @param i the 1-based index of the task to be deleted
     * @return the task that is deleted
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public Task deleteTask(int i)  throws IndexOutOfBoundsException {
        return tasks.remove(i - 1);
    }

    public Task snoozeTask(int i, int numberOfDays) throws IndexOutOfBoundsException, DukeException.DateException {
        Task t = tasks.get(i - 1);
        if (t instanceof Task.Deadline) {
            ((Task.Deadline) t).snooze(numberOfDays);
        } else {
            throw new DukeException.DateException();
        }
        return t;
    }

    /**
     * @return the number of tasks in the list
     */
    public int numberOfTasks() {
        return tasks.size();
    }

    /**
     * creates a new TaskList containing only tasks from current list that match given string
     * @param keyword string to search for
     * @return new TaskList containing tasks that have description matching given string
     */
    public TaskList filter(String keyword) {
        TaskList filteredTasks = new TaskList();
        for (Task t: tasks) {
            if (t.desc.matches(String.format(".*\\b%s\\b.*", keyword))) {
                filteredTasks.addTask(t);
            }
        }
        return filteredTasks;
    }

    /**
     * @return a formatted String of all tasks for writing to file
     */
    public ArrayList<String> toRepr() {
        ArrayList<String> arr = new ArrayList<>();
        for (Task t: tasks) {
            arr.add(t.getRepr());
        }
        return arr;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
        return s;
    }
}
