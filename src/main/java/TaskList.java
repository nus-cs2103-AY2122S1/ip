import java.util.ArrayList;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * The TaskList class provides a wrapper around an ArrayList of Tasks to encapsulate the list of tasks given to
 * Side by its user. Supports add, delete and done operations on tasks in it.
 *
 * @author Lua Yi Da
 */

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskLabel;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskLabel = 0;
    }

    /**
     * Calculates the number of tasks in the TaskList.
     *
     * @return Number of tasks in TaskList.
     */
    public int length() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param description String representation of task information.
     */
    public void addTask(String description) {
        Task task = new Task(description);
        this.tasks.add(task);
        taskLabel++;
    }

    /**
     * Adds an event to the TaskList.
     *
     * @param description String representation of event information.
     * @param time Time at which the event happens.
     */
    public void addEvent(String description, String time) {
        Event event = new Event(description, time);
        this.tasks.add(event);
        taskLabel++;
    }

    /**
     * Adds a deadline to the TaskList.
     *
     * @param description String representation of deadline information.
     * @param time Time at which the deadline elapses.
     */
    public void addDeadline(String description, String time) {
        Deadline event = new Deadline(description, time);
        this.tasks.add(event);
        taskLabel++;
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of task to be marked as done.
     * @return String response of Side in response to marking task as done.
     */
    public String markTaskDone(int index) {
        return tasks.get(index).markAsDone();
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index Index of task to be removed.
     * @return String response of Side in response to deleting task.
     */
    public String delete(int index) {
        Task taskToDelete = this.tasks.get(index);
        this.tasks.remove(index);
        taskLabel--;
        String taskQuantifier = this.tasks.size() == 1 ? "task..." : "tasks...";
        String taskCount = "\nYou now have " + tasks.size() + " " + taskQuantifier;
        return "Fine, I'll delete: " + taskToDelete.toString() + taskCount;
    }

    @Override
    public String toString() {
        StringBuilder tasksList = new StringBuilder();
        if (this.taskLabel == 0) {
            return "No tasks yet, stop checking...";
        }

        for (int i = 0; i < this.taskLabel; i++) {
            String fullTaskLine = (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
            tasksList.append(fullTaskLine);
        }
        return "Fine, here are your tasks: \n" + tasksList.toString();
    }
}
