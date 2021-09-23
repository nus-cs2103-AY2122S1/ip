package duke.task;

import duke.Ui;

import java.lang.reflect.Array;
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
     *  @param task task to be added.
     * @param shouldPrint true if should print, false if should not print
     * @return output
     */
    public String add(Task task, boolean shouldPrint) {
        String str = null;
        this.tasks.add(task);
        if (shouldPrint) {
            str = Ui.addTask(task);
            str += Ui.numberOfTasks(tasks);
        }
        return str;
    }

    /**
     * Finishes a task at a given index.
     *
     * @param index index from 1 (i.e lowest index is 1, so subtract 1 to get real index)
     * @return output
     */
    public String finishTask(int index) {
        Task task = this.tasks.get(index - 1);
        return task.doneTask(true);
    }

    /**
     * Lists out the current items in the TaskList.
     * @return output
     */
    public String listOut() {
        return Ui.listTasks(tasks);
    }

    /**
     * This method is to be used when quitting and saving the file.
     *
     * @return the resulting string containing save-friendly information
     */
    public String save() {
        String output = "";
        for (Task task: tasks) {
            output += task.getSaveInfo() + "\n";
        }
        return output;
    }

    /**
     * Deletes a task at a certain index
     *
     * @param index index from 1 (i.e lowest index is 1, so subtract 1 to get real index)
     * @return output
     */
    public String deleteTask(int index) {
        Task item = this.tasks.remove(index - 1);
        String str = Ui.deleteTask(item) + "\n";
        str += Ui.remainingTasks(tasks);
        return str;
    }

    /**
     * Searches for a task given a keyword.
     *
     * @param keyword given keyword
     * @return output
     */
    public String searchTask(String keyword) {
        TaskList matches = new TaskList();
        for (Task t : tasks) {
            if (t.doesNameContain(keyword)) {
                matches.add(t, false);
            }
        }
        String output = Ui.listTasksSearchResults(matches.tasks);
        return output;
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
     * Adds tags to the specified task through the index.
     *
     * @param index index of the task on the list
     * @param tags array list of tags to be added to the task
     * @return output
     */
    public String addTags(int index, ArrayList<String> tags) {
        Task taskToBeTagged = tasks.get(index - 1);
        for (String tag: tags) {
            taskToBeTagged.tag(tag);
        }
        String output = Ui.addTag(taskToBeTagged.toString());
        return output;
    }
}
