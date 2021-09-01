package duke.task;

import java.util.ArrayList;

import duke.Ui;


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
     */
    public String markDone(int taskNumber) {
        String message = "";
        try {
            Task completedTask = tasks.get(taskNumber);
            completedTask.markDone();
            message = Ui.getDoneMessage(completedTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! This is not a valid task number.");
        }
        return message;
    }

    /**
     * Prints the entire TaskList if the TaskList is not empty. If the TaskList is empty,
     * a message that informs the user that they do not have any tasks is printed.
     */
    public String listTasks() {
        StringBuilder message = new StringBuilder();
        if (!tasks.isEmpty()) {
            message.append("Here are the tasks in your list:");
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
    public String addTask(Task task) {
        tasks.add(task);
        return Ui.getAddedMessage(task, tasks.size());
    }

    /**
     * Deletes a specified Task from the TaskList.
     *
     * @param taskNumber The task number of the Task that is to be deleted.
     */
    public String deleteTask(int taskNumber) {
        try {
            Task removedTask = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            return Ui.getDeleteMessage(removedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! This is not a valid task number.";
        }
    }

    /**
     * Prints all the Tasks in the taskList that contain the specified keyword.
     *
     * @param keyword The keyword specified by the user to search for.
     */
    public String findTask(String keyword) {
        boolean canFindMatch = false;
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String currentTask = tasks.get(i).toString();
            if (currentTask.substring(7).matches("(?i)(.*)" + keyword + "(.*)")) {
                if (!canFindMatch) {
                    canFindMatch = true;
                    message.append("Here are the matching tasks in your list:");
                }
                message.append("\n").append(i + 1).append(". ").append(currentTask);
            }
        }
        if (!canFindMatch) {
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
