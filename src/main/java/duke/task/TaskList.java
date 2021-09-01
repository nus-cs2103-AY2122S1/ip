package duke.task;

import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a TaskList object.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class TaskList {

    /** ArrayList to store the tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructor for creating a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for creating a TaskList object.
     *
     * @param tasks ArrayList of tasks.
     * @return a TaskList object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private static Integer getTaskNumber(String input) {
        String[] arr = input.split(" ", 2);
        Integer taskNumber = Integer.parseInt(arr[1]);
        return taskNumber;
    }

    /**
     * Parses input from the user and marks task as done.
     *
     * @param input String that the user inputs.
     */
    public String markTaskDone(String input) {
        try {
            int taskDoneNum = getTaskNumber(input);
            Task taskDone = tasks.get(taskDoneNum - 1);
            taskDone.markAsDone();
            Storage.saveData(this);
            return "Nice! I've marked this task as done:" + '\n' + taskDone.toString();
        } catch (IndexOutOfBoundsException e) {
            return "☹ OOPS!!! No such task can be marked as done!";
        }
    }

    /**
     * Deletes a task from the existing TaskList.
     *
     * @param input String that the user inputs.
     */
    public String deleteTask(String input) {
        try {
            int taskDeleteNum = getTaskNumber(input);
            Task taskToDelete = tasks.get(taskDeleteNum - 1);
            taskToDelete.markUndone();
            tasks.remove(taskDeleteNum - 1);
            Storage.saveData(this);
            return "Noted. I've removed this task: " + taskToDelete.toString() + '\n' + printTaskNumber(this);
        } catch (IndexOutOfBoundsException e) {
            return "☹ OOPS!!! No such task can be deleted!";
        }
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns size of the existing taskList.
     *
     * @return an integer containing the number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index of the Task to be retrieved.
     * @return Task to be retrieved.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns an ArrayList of the existing TaskList.
     *
     * @return ArrayList containing the tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Prints all the tasks in the TaskList.
     *
     * @param tasks a Tasklist object containing the tasks.
     */
    public static String printItemList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + "." + tasks.get(i).toString() + '\n');
        }
        return sb.toString();
    }

    /**
     * Prints the number of tasks in the list.
     *
     * @param tasks a TaskList object containing the tasks.
     */
    public String printTaskNumber(TaskList tasks) {
        return "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
                + " in the list.";
    }

}
