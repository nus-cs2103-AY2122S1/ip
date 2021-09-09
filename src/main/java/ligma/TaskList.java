package ligma;

import java.util.ArrayList;

import ligma.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private int numOfTasks;

    /**
     * Constructor for a TaskList object.
     * @param tasks tasks that this TaskList contains
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numOfTasks = tasks.size();
    }

    /**
     * Adds task t to tasks.
     *
     * @param t Task to be added
     */
    public void addTask(Task t) {
        tasks.add(t);
        numOfTasks++;
    }

    /**
     * Deletes task at given index from tasks.
     *
     * @param index Index of task to be deleted
     */
    public Task deleteTask(int index) {
        numOfTasks--;
        return tasks.remove(index);
    }

    /**
     * Marks task at given index as done.
     *
     * @param index Index of task to be marked done
     */
    public Task markAsDone(int index) {
        return tasks.get(index).markAsDone();
    }

    /**
     * Returns current number of tasks.
     * @return Number of tasks
     */
    public int getTaskAmt() {
        return numOfTasks;
    }

    /**
     * Converts all tasks in this tasklist to a string array.
     *
     * @return the converted string array
     */
    public String[] getStringArr() {
        return tasks.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

    /**
     * Converts all tasks in this tasklist to a string array.
     *
     * @return the converted string array
     */
    public String[] getMetaTasks() {
        return tasks.stream()
                .map(Task::getFullMeta)
                .toArray(String[]::new);
    }

    /**
     * Checks if there are any tasks right now.
     *
     * @return true if no tasks exist
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Searches for tasks containing the target string.
     *
     * @param target string to search for in task descriptions
     * @return array of tasks that contains the target string
     */
    public Task[] find(String target) {
        return tasks.stream()
                .filter(task -> task.match(target))
                .toArray(Task[]::new);
    }

}
