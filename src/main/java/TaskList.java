import java.util.ArrayList;

/**
 * A class that that contains static functions and variables to abstract a task list.
 */
public class TaskList {

    private final static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Add a task to the task list.
     * @param task The task to be added to the task list.
     */
    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it! This task has been added:");
        System.out.println("  " + task);
        System.out.println("You currently have " + numberOfUncompletedTasks()
                + " uncompleted tasks remaining.");
    }

    /**
     * Prints the task list for the user to view.
     */
    public static void listHistory() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * Marks a task in the task list as completed based on the index given.
     * @param index The index of the task list. Note that the index provided starts from 1. So the index 1 represents
     *              the first task in the taskList ArrayList.
     */
    public static void markTaskAsCompleted(int index) {
        if (taskList.get(index - 1).isDone()) {
            System.out.println("This task has already been completed.");
            return;
        }
        System.out.println("congratulations! This task has been completed: ");
        taskList.get(index - 1).setAsFinished();
        System.out.println("  " + taskList.get(index - 1) + "\n");
        System.out.println("You currently have " + numberOfUncompletedTasks()
                + " uncompleted tasks remaining.");
    }

    private static int numberOfUncompletedTasks() {
        int i = 0;
        for (Task task : taskList) {
            if (!task.isDone()) {
                i++;
            }
        }
        return i;
    }
}
