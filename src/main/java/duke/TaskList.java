package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 *
 * @author Joshua Yong
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Class constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded class constructor which initializes the TaskList with existing tasks.
     *
     * @param tasks An ArrayList of tasks to be stored in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void setTaskAsDone(int i) {
        tasks.get(i).setDone();
    }

    public void deleteTask(int i) {
        tasks.remove(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getSize() {
        return tasks.size();
    }

}
