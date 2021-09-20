package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.testinginterface.TaskListInterface;
/**
 * Contains task list and executes operations on task list.
 */
public class TaskList implements TaskListInterface {
    private ArrayList<Task> tasks;

    /**
     * Constructor with an ArrayList argument.
     *
     * @param tasks list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor with no argument.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Returns the list of tasks.
     *
     * @return ArrayList of type Task.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task into the list of tasks.
     *
     * @param task to add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a Task from the list of tasks.
     */
    public void deleteTask(int pos) {
        this.tasks.remove(pos - 1);
    }

    /**
     * Returns a list of tasks that match the date given.
     *
     * @param date given by user.
     * @return ArrayList of type Task of tasks which dates match.
     */
    public ArrayList<Task> checkDate(LocalDate date) {
        ArrayList<Task> results = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.isSameDate(date)) {
                    results.add(d);
                }
            }

            if (t instanceof Event) {
                Event e = (Event) t;
                if (e.isSameDate(date)) {
                    results.add(e);
                }
            }
        }
        return results;
    }

    /**
     * Returns an ArrayList of tasks of which the description contains the given word.
     *
     * @param word given by user.
     * @return List of tasks.
     */
    public ArrayList<Task> find(String word) {
        ArrayList<Task> results = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            if (t.contains(word)) {
                results.add(t);
            }
        }
        return results;
    }

    public void clear() {
        this.tasks = new ArrayList<Task>(100);
    }
}
