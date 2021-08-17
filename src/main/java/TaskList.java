import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class represents the task list stored by the chat bot.
 */
public class TaskList {

    /**
     * Contains tasks added by user.
     */
    private ArrayList<Task> tasks;

    /**
     * Represents the index of the next task to be added by the user.
     */
    private int taskIndex;

    /**
     * Constructor for TaskList.
     */
    private TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    /**
     * Factory method to create a TaskList.
     * @return A new TaskList.
     */
    public static TaskList createTaskList() {
        return new TaskList();
    }

    /**
     * Adds a new task to the list
     * @param task The task description to be added.
     * @param type The type of the task to be added.
     * @param time The time associated with the task added.
     */
    public void addTask(String task, Task.TaskType type, String time) {
        switch(type) {
            case DEADLINE:
                this.tasks.add(new Deadline(task, time));
                break;
            case EVENT:
                this.tasks.add(new Event(task, time));
                break;
            case TODO:
                this.tasks.add(new Todo(task));
                break;
        }
        this.taskIndex++;
    }

    /**
     * Deletes a task.
     * @param taskIndex Index from list to delete task.
     */
    public void deleteTask(int taskIndex) {
        this.tasks.remove(taskIndex - 1);
        this.taskIndex--;
    }

    /**
     * Lists out the tasks inside the list (if any).
     */
    public void listTasks() {
        if (this.taskIndex == 0) {
            System.out.println("No tasks added yet!");
        } else {
            for (int i = 0; i < this.getTasksLength(); i++) {
                System.out.println((i + 1) + "." + this.tasks.get(i));
            }
        }
    }

    /**
     * Marks a task as done.
     * @param taskIndex The index of the task.
     */
    public void markTaskDone(int taskIndex) {
        Task doneTask = this.tasks.get(taskIndex - 1);
        doneTask.markAsDone();
    }

    /**
     * Returns task description from the list using the index.
     * @param index Index of the task on the list.
     * @return Returns task description.
     */
    public String getTask(int index) {
        Task currentTask = tasks.get(index - 1);
        return currentTask.toString();
    }

    /**
     * Returns the length of the current task list.
     * @return Length of tasks.
     */
    public int getTasksLength() {
        return this.taskIndex;
    }
}
