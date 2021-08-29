package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    public static final int MAX_TASKS = 100;
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    /**
     * Adds a new task to the existing list of tasks.
     *
     * @param task Task to be added.
     * @return True if task has been successfully added, false otherwise.
     */
    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }

    /**
     * Deletes a task from the existing list of tasks.
     *
     * @param taskNum Index of the task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int taskNum) {
        return this.taskList.remove(taskNum);
    }

    /**
     * Returns the length of the taskList.
     *
     * @return Length of taskList.
     */
    public int taskListLen() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        int taskListLen = this.taskList.size();
        if (taskListLen == 0) {
            return "There are no tasks in your list.";
        } else {
            StringBuilder msg = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < taskListLen; i++) {
                int currTaskNum = i + 1;
                msg.append("\n").append(currTaskNum).append(". ").append(this.taskList.get(i).toString());
            }
            return msg.toString();
        }
    }
}
