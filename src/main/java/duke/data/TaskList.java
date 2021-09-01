package duke.data;

import java.util.ArrayList;

import duke.commands.Task;
import duke.exceptions.DukeException;

/**
 * Encapsulates a TodoList that contains Tasks.
 *
 * @author Owen Tan
 * @version Duke Level-9
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
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        if (lst.size() == 0) {
            System.out.println("---- No Tasks currently ----");
        }
        int i = 1;
        for (Task s : lst) {
            System.out.println(i + "." + s);
            i++;
        }
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
     * @throws DukeException
     */
    public void complete(int index) throws DukeException {
        if (index < 0 || index > lst.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        Task task = lst.get(index);
        task.completeTask();
    }

    /**
     * Deletes a Task in a Tasklist with a given index.
     *
     * @param index Index of Task in the List (0-indexed)
     * @throws DukeException
     */
    public void delete(int index) throws DukeException {
        if (index < 0 || index > lst.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
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

    public Task get(int index) throws DukeException {
        if (index < 0 || index > lst.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        Task task = lst.get(index);
        return task;
    }

    /**
     * Prints all relevant tasks that contain a certain keyword.
     *
     * @param keyword Keyword to be contained in the Task's description.
     */
    public void printMatchingTasks(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int i = 1;
        for (Task task : lst) {
            if (task.containsKeyword(keyword)) {
                System.out.println(i + "." + task);
                i++;
            }
        }

        if (i == 1) {
            System.out.println("---- No related tasks found ----");
        }
    }
}
