import java.util.ArrayList;

/**
 * A class that abstracts a list of tasks set by the user.
 */
public class TaskList {

    private final ArrayList<Task> taskList = new ArrayList<>();
    private int uncompletedTasks = 0;

    public TaskList() {}

    /**
     * Add a task to the task list.
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        this.uncompletedTasks++;
        System.out.println("Got it! This task has been added:");
        System.out.println(task);
        System.out.println("You currently have " + this.uncompletedTasks
                + " uncompleted tasks remaining.\n");
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
     * Marks a task in the task list as completed based on the index given.
     * @param index The index of the task list. Note that the index provided starts from 1. So the index 1 represents
     *              the first task in the taskList ArrayList.
     */
    public void markTaskAsCompleted(int index) {
        if (index > this.taskList.size()) {
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
        System.out.println("You currently have " + this.uncompletedTasks
                + " uncompleted tasks remaining.\n");
    }
}
