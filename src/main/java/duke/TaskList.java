package duke;

import duke.exception.*;

import java.util.ArrayList;

/**
 * A class that represents a list of Tasks.
 *
 * It contains a constructor, a getter,
 * a method to print a list, add a task to a list,
 * mark a task as done and delete a task.
 *
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor to create a new TaskList.
     *
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor to create a new TaskList.
     *
     * @param tasks An ArrayList of Task that contains Tasks to be put inside the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method to print out tasks inside a TaskList.
     *
     * @return String representation of all the tasks inside the current TaskList.
     */
    public String printList() {
        String str = "Here are the tasks on your list: \n";
        for (int i = 1; i <= tasks.size(); i++) {
            str = str + i + ". " + tasks.get(i-1) + "\n";
        }
        return str;
    }

    /**
     * A method to mark a task as done.
     *
     * @param index index of the task to be marked as done.
     * @return String representation of the task after marked as done.
     */
    public String markAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        String result = "Nice! I have marked this task as done!\n" + task.toString() ;
        return result;
    }

    /**
     * A method to mark a task as undone.
     *
     * @param index index of the task to be marked as undone.
     * @return String representation of the task after marked as undone.
     */
    public String markAsUndone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsUndone();
        String result = "Nice! I have marked this task as undone!\n" + task.toString() ;
        return result;
    }

    /**
     * A method to add a Task inside a TaskList
     *
     * @param task Task to be added.
     * @return Task after it is added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        String result = "Got it. I've added this task.\n"
                + task.toString() + "\r\n"
                + "Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task in the list" : " tasks in the list.");
        return result;
    }

    /**
     * A method to delete a certain in a TaskList.
     *
     * @param index index of task to be deleted.
     * @return String representation of task after it is deleted.
     */
    public String deleteTask(int index) {
        Task task = tasks.remove(index - 1);
        String result = "Noted. I've removed this task: \n" + task.toString() + "\r\n"
                + "Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task in the list" : " tasks in the list.");
        return result;
    }

    /**
     * Method to find a task given an input string.
     *
     * @param str Input string from user to find a certain task.
     * @return TaskList which contains all the tasks that match.
     * @throws TaskDoesNotExistException when there is no task that matches.
     */

    public String findTask(String str)throws TaskDoesNotExistException {
        String t = str.substring(5);
        ArrayList<Task> l = new ArrayList<>();

        for (Task task: tasks) {
            if (task.getTitle().contains(t)) {
                l.add(task);
            }
        }

        if (l.isEmpty()) {
            throw new TaskDoesNotExistException();
        }
        String result = "Here are the matching tasks in your list: \n";
        TaskList list = new TaskList(l);
        result += list.printList() + "\r\n";
        return result;
    }

    /**
     * Getter to get ArrayList
     *
     * @return ArrayList of the current tasks.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }
}
