package duke.task;

import duke.Ui;

import java.util.ArrayList;

/**
 * This class represents a TaskList, which is the list of tasks that Duke refers to to carry out commands provided
 * to him.
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList, which creates a new array of Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task task to be added.
     * @param shouldPrint true if should print, false if should not print
     */
    public void add(Task task, boolean shouldPrint) {
        this.tasks.add(task);
        if (shouldPrint) {
            Ui.addTask(task);
            Ui.numberOfTasks(tasks);
        }
    }

    /**
     * Finishes a task at a given index.
     *
     * @param index index from 1 (i.e lowest index is 1, so subtract 1 to get real index)
     */
    public void finishTask(int index) {
        Task task = this.tasks.get(index - 1);
        task.doneTask(true);
    }

    /**
     * Lists out the current items in the TaskList.
     */
    public void listOut() {
        Ui.listTasks(tasks);
    }

    /**
     * This method is to be used when quitting and saving the file.
     *
     * @return the resulting string containing save-friendly information
     */
    public String save() {
        String output = "";
        for (Task task: tasks) {
            output += task.getType() + " | " + (task.getDone() ? "1 | " : "0 | ") + task.getSaveInfo() + "\n";
        }
        return output;
    }

    /**
     * Deletes a task at a certain index
     *
     * @param index index from 1 (i.e lowest index is 1, so subtract 1 to get real index)
     */
    public void deleteTask(int index) {
        Task item = this.tasks.remove(index - 1);
        Ui.deleteTask(item);
        Ui.remainingTasks(tasks);
    }

    /**
     * Searches for a task given a keyword.
     */
    public void searchTask(String keyword) {
        TaskList matches = new TaskList();
        for (Task t : tasks) {
            if (t.doesNameContain(keyword)) {
                matches.add(t, false);
            }
        }
        matches.displaySearchResults();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return number of tasks in the list.
     */
    private int numTasks() {
        return tasks.size();
    }

    /**
     * Displays search results.
     */
    public void displaySearchResults() {
        Ui.listTasksSearchResults(tasks);
    }
}
