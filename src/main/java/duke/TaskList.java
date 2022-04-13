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
    public int getSize() {
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
    public Task completeTask(int index) {
        Task completedTask = tasks.get(index);
        completedTask.markAsDone();
        return completedTask;
    }

    /**
     * Search tasks based on keyword
     *
     * @param key
     * @return list of tasks based on searched keyword
     */
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
