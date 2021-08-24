package duke.data;

import duke.data.task.Task;

import java.util.ArrayList;

/**
 * This class abstracts the task list of Duke.
 *
 * @author Chesterwongz
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs a TaskList populated by Tasks from the given ArrayList of Tasks.
     *
     * @param taskList The ArrayList of Tasks to be used.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Adds a new Task to the TaskList.
     *
     * @param newTask The new Task to be added.
     */
    public void add(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param index The index of the task to be removed
     * @return The removed Task.
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }
}
