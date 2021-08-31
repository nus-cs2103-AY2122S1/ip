package ligma;

import java.util.ArrayList;

import ligma.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private int noOfTasks;

    /**
     * Constructor for a TaskList object.
     * @param tasks tasks that this TaskList contains
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.noOfTasks = tasks.size();
    }

    /**
     * Adds task t to tasks.
     *
     * @param t Task to be added
     */
    public void addTask(Task t) {
        tasks.add(t);
        noOfTasks++;
    }

    /**
     * Deletes task at given index from tasks.
     *
     * @param index Index of task to be deleted
     */
    public Task deleteTask(int index) {
        noOfTasks--;
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
        return noOfTasks;
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
     * Searches for tasks containing the target string.
     *
     * @param target string to search for in task descriptions
     * @return array of tasks that contains the target string
     */
    public Task[] find(String target) {
        return tasks.stream().filter(task -> task.match(target)).toArray(Task[]::new);
    }

}
