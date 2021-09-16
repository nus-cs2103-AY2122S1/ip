package aoi.data;

import java.util.ArrayList;

import aoi.commands.Task;
import aoi.exceptions.AoiException;

/**
 * Encapsulates a TodoList that contains Tasks.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class TaskList {
    /** A list of tasks */
    private ArrayList<Task> lst;

    /**
     * Public constructor for TaskList.
     */
    public TaskList() {
        lst = new ArrayList<Task>();
    }

    /**
     * Prints all items in a TaskList.
     */
    public String printTaskList() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        if (lst.size() == 0) {
            str.append("---- No Tasks currently ----\n");
        }
        int i = 1;
        for (Task s : lst) {
            str.append(i + "." + s + "\n");
            i++;
        }
        return str.toString();
    }

    /**
     * Adds a Task to TaskList.
     *
     * @param text
     */
    public void add(Task text) {
        lst.add(text);
    }

    /**
     * Completes a Task in a Tasklist with a given index.
     *
     * @param index Index of Task in the List (0-indexed)
     * @throws AoiException
     */
    public void complete(int index) throws AoiException {
        if (index < 0 || index > lst.size() - 1) {
            throw new AoiException("Hey! Invalid Task number given.");
        }
        Task task = lst.get(index);
        task.completeTask();
    }

    /**
     * Deletes a Task in a Tasklist with a given index.
     *
     * @param index Index of Task in the List (0-indexed)
     * @throws AoiException
     */
    public void delete(int index) throws AoiException {
        if (index < 0 || index > lst.size() - 1) {
            throw new AoiException("Hey! Invalid Task number given.");
        }
        lst.remove(index);
    }

    public ArrayList<Task> getList() {
        return lst;
    }

    /**
     * Returns a length of TaskList.
     *
     * @return An integer that represents the number of Tasks in a TaskList.
     */
    public int getLength() {
        return lst.size();
    }

    public Task get(int index) throws AoiException {
        if (index < 0 || index > lst.size() - 1) {
            throw new AoiException("Hey! Invalid Task number given.");
        }
        Task task = lst.get(index);
        return task;
    }

    /**
     * Prints all relevant tasks that contain a certain keyword.
     *
     * @param keyword Keyword to be contained in the Task's description.
     */
    public String printMatchingTasks(String keyword) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task task : lst) {
            if (task.containsKeyword(keyword)) {
                str.append(i + "." + task + "\n");
                i++;
            }
        }

        if (i == 1) {
            str.append("---- No related tasks found ----\n");
        }
        return str.toString();
    }
}
