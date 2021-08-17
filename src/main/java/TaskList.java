import java.util.ArrayList;

/**
 * This class represents the task list stored by the chat bot.
 */
public class TaskList {

    /**
     * Contains tasks added by user.
     */
    private Task[] tasks;

    /**
     * Represents the index of the next task to be added by the user.
     */
    private static int taskIndex;

    /**
     * Constructor for TaskList.
     */
    private TaskList() {
        this.tasks = new Task[100];
    }

    /**
     * Factory method to create a TaskList.
     * @return A new TaskList.
     */
    public static TaskList createTaskList() {
        return new TaskList();
    }

    /**
     * Adds a new task to the list.
     * @param task The task description to be added.
     */
    public void addTask(String task) {
        Task newTask = Task.createTask(task);
        tasks[taskIndex] = newTask;
        taskIndex++;
    }

    /**
     * Lists out the tasks inside the list (if any).
     */
    public void listTasks() {
        if (taskIndex == 0) {
            System.out.println("No tasks added yet!");
        } else {
            for (int i = 0; i < taskIndex; i++) {
                System.out.println((i + 1) + ". " + this.tasks[i].getTaskDescription());
            }
        }
    }
}
