package jarvis;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the task list and all relevant list methods
 */
public class TaskList {
    private static ArrayList<Task> taskList;
    private static int counter;

    /**
     * Creates the taskList and a counter to keep track of the number of tasks saved in the user's
     * hard disk at any point in time.
     */
    public TaskList() {
        taskList = new ArrayList<Task>(100);
        counter = 0;
    }

    /**
     * Returns the taskList.
     *
     * @return the taskList
     */
    public static ArrayList<Task> getTaskList() {
        assert (taskList.size() == 100) : "Task list array has not been created.";
        return TaskList.taskList;
    }

    /**
     * Returns the number of tasks currently save in user's hard disk.
     *
     * @return the number of tasks currently save in user's hard disk (counter member)
     */
    public static int getCounter() {
        assert (taskList.size() == 100) : "Task list array has not been created.";
        return TaskList.counter;
    }

    /**
     * Adds a task to the taskList.
     *
     * @param currTask the task that is to be added to the taskList
     */
    public static void addTask(Task currTask) {
        assert (taskList.size() == 100) : "Task list array has not been created.";

        // Add the new task to the task list and increment the number of tasks by 1
        TaskList.taskList.add(currTask);
        TaskList.counter++;
    }

    /**
     * Adds a task to the taskList and update the list of tasks in user's hard disk.
     *
     * @param currTask the task that is to be added
     * @throws IOException if there is an error in appending the task to the list of tasks
     * in user's hard disk
     */
    public static String addTaskAndUpdate(Task currTask) throws IOException {
        assert (taskList.size() == 100) : "Task list array has not been created.";

        // Add the new task to the task list and increment the number of tasks by 1
        TaskList.taskList.add(currTask);
        TaskList.counter++;

        Storage.appendToTaskFile(); //Append the new task to the task file
        return Ui.taskAdded(currTask); //Output to be printed to the user by Jarvis
    }

    /**
     * Deletes a task to the taskList and update the list of tasks in user's hard disk.
     *
     * @param taskNum Number of the task that is to be deleted
     * @throws IOException if there is an error in re-writing the list of tasks without the
     * deleted task
     */
    public static String deleteTaskAndUpdate(int taskNum) throws IOException {
        assert (taskList.size() == 100) : "Task list array has not been created.";

        // Delete the task and decrement the number of tasks in the task list by 1
        Task currTask = TaskList.getTaskList().get(taskNum);
        TaskList.getTaskList().remove(currTask);
        TaskList.counter--;

        Storage.rewriteTaskFile(); // Rewrite the task file to remove the deleted task

        return Ui.taskDeleted(currTask);
    }

    /**
     * Marks a given task as completed and to update its status icon in the list of tasks
     * in user's hard disk.
     *
     * @throws IOException if there is an error when overwriting/appending to the contents of
     * the file
     */
    public static String markAsDoneAndUpdate(int taskNum) throws IOException{
        TaskList.getTaskList().get(taskNum).isDone = true; // Mark the task as done

        Storage.rewriteTaskFile(); // Rewrite the task file to store the newly done task

        return Ui.taskDone(taskNum); // Display output by Jarvis
    }
}
