import java.util.ArrayList;

/**
 * Encapsulates all information for a list of Tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Prints out the taskList to the console
     */
    public void printList() throws EmptyTaskListException {
        if (this.taskList.isEmpty()) {
            throw new EmptyTaskListException("You have no tasks!");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

    /**
     * Marks the taskNum-th item in the tasks list as completed.
     * @param taskNum The index of the task to be marked.
     */
    public void doTask(int taskNum) {
        taskList.get(taskNum).doTask();
    }

    /**
     * Adds a task to the taskList and prints a success message.
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
        System.out.println("Alright, I've added the following task:\n");
        System.out.println(taskList.get(taskList.size() - 1) + "\nNow you have " + taskList.size() + " tasks in the list.\n");
    }

    /**
     * Removes a task from the taskList.
     * @param task The task to be removed.
     */
    public void delete(int taskIndex) {
        taskList.remove(taskIndex);
    }
}
