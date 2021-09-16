package duke.task;

import java.util.ArrayList;
/**
 * Contains the list of tasks from the text file that was saved. Various methods
 * implemented to manipulate the list of tasks.
 *
 * @author Benjamin Lui
 */
public class TaskList {

    private ArrayList<Task> listOfTasks;

    /**
     * Constructor for the TaskList class when there is no tasks in the list.
     */
    public TaskList() {
        listOfTasks = new ArrayList<Task>();
    }

    /**
     * Constructor for the TaskList class to use a current list of tasks.
     * @param lst the list to be used for the list of tasks manipulation
     */
    public TaskList(ArrayList<Task> lst) {
        listOfTasks = lst;
    }

    /**
     * Adds a task to the list.
     * @param task task to be added to the list
     */
    public void add(Task task) {
        listOfTasks.add(task);
    }

    /**
     * Checks whether a task is currently in the task list.
     * @param task the task to be checked against the task list
     * @return true if and only if the task is in the task list
     */
    public boolean contains(Task task) {
        for (Task tsk : listOfTasks) {
            if (tsk.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a particular task from the list based on its task number on the list.
     * @param taskNumber the task number of the task to be removed
     * @return the task that was removed
     */
    public Task delete(int taskNumber) {
        boolean isList;
        assert isList = listOfTasks.size() - 1 > taskNumber : "task doesn't exist";
        return listOfTasks.remove(taskNumber);
    }

    /**
     * Marks the task at the task number as done.
     * @param taskNumber the task to be marked as done
     */
    public void done(int taskNumber) {
        try {
            Task currTask = listOfTasks.get(taskNumber);
            currTask.markAsDone();
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Index out of bounds");
            return;
        }
    }

    /**
     * Returns the number of tasks currently in the list.
     * @return the number of tasks in the list
     */
    public int size() {
        return listOfTasks.size();
    }

    /**
     * Returns the list of tasks as an array list.
     * @return the list of tasks
     */
    public ArrayList<Task> getAllTasks() {
        return listOfTasks;
    }

    public void clearTasks() {
        listOfTasks = new ArrayList<Task>();
    }
}
