/**
 * The task list to save all the tasks
 */
public class TaskList {
    // Saved tasks
    private final Task[] tasks;

    public TaskList() {
        this.tasks = new Task[100];
    }

    // Index in the tasks array of the next task
    private int nextTaskIndex = 0;

    /**
     * Add a task to the list
     * @param task The added task.
     */
    public void addTask(Task task) {
        tasks[nextTaskIndex] = task;
        nextTaskIndex++;
        PrintUtil.insertSeparateLine();
        PrintUtil.displayContent("Got it. I've added this task:");
        PrintUtil.displayContent("  " + task.toString());
        PrintUtil.displayContent("Now you have " + (nextTaskIndex) + " tasks in the list.");
        PrintUtil.insertSeparateLine();
    }

    /**
     * Display the task list
     */
    public void displayTaskList() {
        PrintUtil.insertSeparateLine();
        PrintUtil.displayContent("Here are the tasks in your list:");
        for (int i = 0; i < nextTaskIndex; i++) {
            PrintUtil.displayContent((i + 1) + "." + tasks[i].toString());
        }
        PrintUtil.insertSeparateLine();
    }

    /**
     * Mark a task in the given index in the list as done
     *
     * @param index The desired index.
     */
    public void markTaskAsDone(int index) {
        tasks[index - 1].markAsDone();
        PrintUtil.insertSeparateLine();
        PrintUtil.displayContent("Nice! I've marked this task as done:");
        PrintUtil.displayContent("  " + tasks[index - 1].toString());
        PrintUtil.insertSeparateLine();
    }
}
