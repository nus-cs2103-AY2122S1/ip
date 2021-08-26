package duke.task;

import duke.Ui;

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
     */
    public void markDone(int taskNumber) {
        try {
            Task completedTask = this.tasks.get(taskNumber);
            completedTask.markDone();
            Ui.printDoneMessage(completedTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tOOPS!!! This is not a valid task number.");
        }
    }

    /**
     * Prints the entire TaskList if the TaskList is not empty. If the TaskList is empty,
     * a message that informs the user that they do not have any tasks is printed.
     */
    public void listTasks() {
        if (!this.tasks.isEmpty()) {
            Ui.printLine();
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i));
            }
            Ui.printLine();
        } else {
            System.out.println("\tYou don't have any tasks in your list!");
        }
    }

    /**
     * Adds a specified Task into the TaskList.
     *
     * @param task The specified Task to add into the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        Ui.printAddedMessage(task, this.tasks.size());
    }

    /**
     * Deletes a specified Task from the TaskList.
     *
     * @param taskNumber The task number of the Task that is to be deleted.
     */
    public void deleteTask(int taskNumber) {
        try {
            Task removedTask = this.tasks.get(taskNumber);
            this.tasks.remove(taskNumber);
            Ui.printDeleteMessage(removedTask, this.tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tOOPS!!! This is not a valid task number.");
        }
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
        return this.tasks.size();
    }
}
