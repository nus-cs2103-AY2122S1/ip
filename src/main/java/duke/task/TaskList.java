package duke.task;

import java.util.ArrayList;


/**
 * Class that contains the task list and operations on the task list.
 */
public class TaskList {

    /** The ArrayList that represents the list of Tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList using a given ArrayList
     *
     * @param tasks The Arraylist used to construct the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a specified task in the TaskList as done.
     *
     * @param taskNumber The task number that is used to specify which task to mark as done.
     * @return The task that has just been marked as done.
     * @throws IndexOutOfBoundsException If the number specified by the user is
     * larger than the size of the Tasklist or is less than 0, an IndexOutOfBoundsException
     * will be thrown.
     */
    public Task markDone(int taskNumber) throws IndexOutOfBoundsException {
        Task completedTask = tasks.get(taskNumber - 1);
        completedTask.markDone();
        return completedTask;
    }

    /**
     * Prints the entire TaskList if the TaskList is not empty. If the TaskList is empty,
     * a message that informs the user that they do not have any tasks is printed.
     *
     * @return A String of the list of tasks in the task list. If the user does not have
     * tasks, the String returned will inform the user accordingly.
     */
    public String listTasks() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");

        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                message.append("\n")
                        .append(i + 1)
                        .append(". ")
                        .append(tasks.get(i));
            }
            return message.toString();
        } else {
            return "You don't have any tasks in your list!";
        }
    }

    /**
     * Adds a specified Task into the TaskList.
     *
     * @param task The specified Task to add into the TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a specified Task from the Tasklist.
     *
     * @param taskNumber The specified Task to delete from the TaskList.
     * @return The deleted Task.
     * @throws IndexOutOfBoundsException If the number specified by the user is
     * larger than the size of the Tasklist or is less than 0, an IndexOutOfBoundsException
     * will be thrown.
     */
    public Task deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        Task removedTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return removedTask;
    }

    /**
     * Prints all the Tasks in the taskList that contain the specified keyword.
     *
     * @param keyword The keyword specified by the user to search for.
     * @return A String of the list of tasks found using the keyword.
     */
    public String findTask(String keyword) {
        boolean hasFoundMatch = false;
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            String currentTask = tasks.get(i).toString();
            boolean isFound = currentTask.substring(7).matches("(?i)(.*)" + keyword + "(.*)");
            if (isFound) {
                hasFoundMatch = true;
                message.append("\n").append(i + 1).append(". ").append(currentTask);
            }
        }
        if (!hasFoundMatch) {
            return "Sorry!, I couldn't find any tasks with that keyword.";
        }
        return message.toString();
    }

    /**
     * Retrieves a specified Task from the TaskList.
     *
     * @param taskNumber The task number of the specified Task.
     * @return The specified Task.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     *
     * @return The number of Tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }
}
