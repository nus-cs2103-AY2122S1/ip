public class TaskList {
    // Saved tasks
    private final Task[] tasks;

    public TaskList() {
        this.tasks = new Task[100];
    }

    // Index in the tasks array of the next task
    private int nextTaskIndex = 0;

    /**
     * Print out the separation line between elements of the program
     */
    private static void insertSeparateLine() {
        String separateLine = "____________________________________________________________";
        System.out.println("\t" + separateLine);
    }

    /**
     * Print out the formatted version of any string content
     *
     * @param content Content to display.
     */
    private static void displayContent(String content) {
        System.out.println("\t" + " " + content);
    }

    /**
     * Print out the formatted version of any string content between two horizontal lines
     *
     * @param content Content to display.
     */
    private static void displayContentBetweenLines(String content) {
        insertSeparateLine();
        System.out.println("\t" + " " + content);
        insertSeparateLine();
    }

    /**
     * Add a task to the list
     * @param task The added task.
     */
    public void addTask(Task task) {
        tasks[nextTaskIndex] = task;
        nextTaskIndex++;
        displayContentBetweenLines("added: " + task.description);
    }

    /**
     * Display the task list
     */
    public void displayTaskList() {
        insertSeparateLine();
        displayContent("Here are the tasks in your list:");
        for (int i = 0; i < nextTaskIndex; i++) {
            displayContent((i + 1) + "." + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        insertSeparateLine();
    }

    /**
     * Mark a task in the given index in the list as done
     *
     * @param index The desired index.
     */
    public void markTaskAsDone(int index) {
        tasks[index - 1].markAsDone();
        insertSeparateLine();
        displayContent("Nice! I've marked this task as done:");
        displayContent("  " + "[" + tasks[index - 1].getStatusIcon() + "] " + tasks[index - 1].getDescription());
        insertSeparateLine();
    }
}
