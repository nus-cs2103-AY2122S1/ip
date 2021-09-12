package duke;

import java.util.ArrayList;

import duke.task.Achievable;
import duke.task.Task;

/**
 * Class to encapsulate a TaskList
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Class constructor for TaskList Class when not loading data
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Class constructor for TaskList Class when loading data
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Marks the task with the given task number as done
     *
     * @param taskNumber the task number of the task
     * @return           the task marked as done
     */
    public Task markAsDone(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.markAsDone();
        return task;
    }

    /**
     * Adds the given task to the TaskList
     *
     * @param task a task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task with the given task number
     *
     * @param taskNumber the task number of the task
     * @return           the task removed
     */
    public Task remove(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        this.tasks.remove(task);
        return task;
    }

    /**
     * Changes the date of the task with the given task number
     *
     * @param taskNumber the task number of the task
     * @param taskDate   the task date of the task
     * @return           the task modified
     */
    public Task changeDate(int taskNumber, String taskDate) {
        Task task = this.tasks.get(taskNumber);
        if (task instanceof Achievable) {
            Achievable achievable = (Achievable) task;
            achievable.changeDate(taskDate);
        }
        return task;
    }

    /**
     * Changes the description of the task with the given task number
     *
     * @param taskNumber      the task number of the task
     * @param taskDescription the task description of the task
     * @return                the task modified
     */
    public Task changeDescription(int taskNumber, String taskDescription) {
        Task task = this.tasks.get(taskNumber);
        task.changeDescription(taskDescription);
        return task;
    }

    /**
     * Mark the task with the given task number as incomplete
     *
     * @param taskNumber      the task number of the task
     * @return                the task modified
     */
    public Task markAsIncomplete(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.markAsIncomplete();
        return task;
    }

    /**
     * Finds the tasks that match the given description
     *
     * @param description the description of tasks to be found
     * @return            the tasks that match the description
     */
    public ArrayList<Task> match(String description) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns the task list
     *
     * @return           the task list
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the toString of TaskList
     *
     * @return           the toString of TaskList
     */
    @Override
    public String toString() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
