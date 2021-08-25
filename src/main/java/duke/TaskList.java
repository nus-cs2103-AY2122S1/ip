package duke;

import java.util.ArrayList;

import duke.task.Task;

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
     * Mark the task with the given task number as done
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
     * Add the given task to the TaskList
     *
     * @param task a task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Remove the task with the given task number
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
     * Return the task list
     *
     * @return           the task list
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Return the toString of TaskList
     *
     * @return           the toString of TaskList
     */
    @Override
    public String toString() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
