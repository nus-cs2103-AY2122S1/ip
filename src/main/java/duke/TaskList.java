package duke;

import java.util.ArrayList;

/**
 * Class that deals with task operations.
 */
public class TaskList {

    protected ArrayList<Task> tasks;

    /**
     * Initializes a TaskList object.
     * @param tasks an ArrayList of type Task
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
    }

    /**
     * Returns the size of the list
     * @return the size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the list
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from the list
     * @param index
     * @return the task deleted
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks the task as completed
     * @param index
     * @return the completed task
     */
    public Task markTaskDone(int index) {
        Task doneTask = tasks.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    /**
     * Retrieves the task inputted
     * @param index
     * @return the task found in inputted index in the list.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public String findTasks (String keyword) {
        String output = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String name = t.description;
            if (name.contains(keyword)) {
                output += (i + 1) + ". " + t.toString() + "\n";
            }
        }
        return output;
    }

    @Override
    public String toString() {
        String str = "";
        int ctr = 1;
        for (Task t : tasks) {
            str += ctr + ". " + t + "\n";
            ctr++;
        }
        return str;
    }
}
