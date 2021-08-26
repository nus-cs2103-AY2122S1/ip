package duke;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    /**
     * TaskList constructor when there are tasks in file
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * TaskList constructor when there are no tasks in file
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    /**
     * Gives the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Add task to task list
     *
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete task from task list
     *
     * @param index
     * @return deleted task
     */
    public Task deleteTask(int index) {
        Task deleteTask = tasks.get(index);
        tasks.remove(index);
        return deleteTask;
    }

    /**
     * Mark task as done
     *
     * @param index
     * @return task that is marked as done
     */
    public Task doneTask(int index) {
        Task doneTask = tasks.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    /**
     * Get task based on index
     *
     * @param index
     * @return task based on index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    public String find(String key) {
        String taskList = "";
        int count = 1;
        for (Task t : tasks) {
            if (t.description.contains(key)) {
                taskList = taskList + count + "." + t + "\n";
                count += 1;
            }
        }
        return taskList;
    }

    /**
     * Overrides toString method
     *
     * @return list of tasks in task list
     */
    @Override
    public String toString() {
        String taskList = "";
        int count = 1;
        for (Task t : tasks) {
            taskList = taskList + count + "." + t + "\n";
            count += 1;
        }
        return taskList;
    }
}
