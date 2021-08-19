/**
 * The task list to save all the tasks
 */
public class TaskList {
    // Saved tasks
    private final Task[] tasks;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.tasks = new Task[100];
    }

    // Index in the tasks array of the next task
    private int nextTaskIndex = 0;

    /**
     * Add a task to the list
     *
     * @param task The added task.
     */
    public String addTask(Task task) {
        tasks[nextTaskIndex] = task;
        nextTaskIndex++;
        
        StringBuilder response = new StringBuilder("Got it. I've added this task:");
        response.append("\t").append("  ").append(task).append("\n\t Now you have ").append(nextTaskIndex);
        if ((nextTaskIndex) == 1) {
            response.append(" task in the list.");
        } else {
            response.append(" tasks in the list.");
        }
        return response.toString();
    }

    /**
     * Mark a task in the task list as done
     * 
     * @param index The user requested index.
     * @return The status of the operation.
     */
    public String markTaskAsDone(int index) {
        StringBuilder response = new StringBuilder();
        if (tasks[index - 1].markAsDone()) {
            response.append("Nice! I've marked this task as done:\n");
        } else {
            response.append("This task is already done!");
        }
        response.append("\t" + "  ").append(tasks[index - 1].toString());
        return response.toString();
    }

    /**
     * Return the size of the task list
     * 
     * @return Size of the task list.
     */
    public int size() {
        return nextTaskIndex;
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "There is no task in the list";
        } else {
            StringBuilder res = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < nextTaskIndex; i++) {
                String temp = "\t" + " " + (i + 1) + "." + tasks[i].toString();
                if (i < nextTaskIndex - 1) { // remove the last \n char, ugly but get the job done
                    temp += "\n";
                }
                res.append(temp);
            }
            return res.toString();
        }
    }
}
