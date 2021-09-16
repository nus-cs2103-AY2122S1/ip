package jwbot.data;

import java.util.ArrayList;
import java.util.List;

import jwbot.data.task.Deadline;
import jwbot.data.task.Event;
import jwbot.data.task.Task;
import jwbot.data.task.Todo;

/**
 * Class that stores the list of the tasks.
 *
 * @author  Yim Jaewon
 */
public class TaskList {

    private List<Task> prevTasks;
    private List<Task> tasks;

    /**
     * The basic constructor of the task list that takes in an arraylist of tasks.
     *
     * @param tasks the list of the tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.prevTasks = new ArrayList<>();
        backupTasks();
    }

    /**
     * The alternative constructor made in case an error occurs.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        this.prevTasks = new ArrayList<>();
    }

    /**
     * The method used to access the task arraylist directly.
     *
     * @return the stored private arraylist of the tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Removes the item from the list and return it.
     *
     * @param index the index of the item of the list that the user wants to remove
     * @return the removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Get the number of tasks stored currently.
     *
     * @return the size of the stored task arraylist
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Add the task to the list.
     *
     * @param task the task that the user wants to add to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Get task from the list.
     *
     * @param index the index of the list of the tasks that the user wants to get the task
     * @return the task from the list of the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void undoCommand() {
        tasks = deepCopy(prevTasks);
    }

    public void backupTasks() {
        prevTasks = deepCopy(tasks);
    }

    public List<Task> deepCopy(List<Task> tasks) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Todo) {
                result.add(new Todo((Todo) task));
            }
            if (task instanceof Event) {
                result.add(new Event((Event) task));
            }
            if (task instanceof Deadline) {
                result.add(new Deadline((Deadline) task));
            }
        }
        return result;
    }
}
