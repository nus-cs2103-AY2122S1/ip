import java.util.ArrayList;

/**
 * A class that abstracts a list of tasks set by the user.
 */
public class TaskList {

    /** An arraylist that contains the tasks set by the user. */
    private final ArrayList<Task> taskList = new ArrayList<>();
    /** The number of uncompleted tasks in the task list */
    private int uncompletedTasks = 0;
    /** The total number of tasks in the task list. */
    private int totalTasks = 0;

    public TaskList() {}

    /**
     * Add a task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        this.uncompletedTasks++;
        totalTasks++;
        System.out.println("Got it! This task has been added:");
        System.out.println(task);
        this.printTaskListStatus();
    }

    /**
     * Removes a task in the task list based on the index given.
     * @param index he index of the task list. Note that the index provided starts from 1. So the index 1 represents
     *              the first task in the taskList ArrayList.
     */
    public void removeTask(int index) {
        if (index > this.taskList.size() || index <= 0) {
            System.out.println("This entry does not exist.\n");
            return;
        }
        System.out.println("Understood. I've removed this task:");
        Task task = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        System.out.println(task);
        if (!task.isDone()) {
            uncompletedTasks--;
        }
        totalTasks--;
        this.printTaskListStatus();
    }

    /**
     * Marks a task in the task list as completed based on the index given.
     * @param index The index of the task list. Note that the index provided starts from 1. So the index 1 represents
     *              the first task in the taskList ArrayList.
     */
    public void markTaskAsCompleted(int index) {
        if (index > this.taskList.size() || index <= 0) {
            System.out.println("This entry does not exist.\n");
            return;
        }
        if (this.taskList.get(index - 1).isDone()) {
            System.out.println("This task has already been completed.\n");
            return;
        }
        System.out.println("congratulations! This task has been completed:");
        this.taskList.get(index - 1).setAsFinished();
        this.uncompletedTasks--;
        System.out.println(this.taskList.get(index - 1));
        this.printTaskListStatus();
    }

    /**
     * Prints the task list for the user to view.
     */
    public void listHistory() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * Prints a message that tells the user the number of tasks in his list along with
     * the number of uncompleted tasks.
     */
    private void printTaskListStatus() {
        System.out.println("You currently have " + this.totalTasks + " tasks in your list with "
                + this.uncompletedTasks + " uncompleted tasks remaining.\n");
    }

}
