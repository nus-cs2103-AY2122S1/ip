package duke;

import duke.exceptions.TaskOutOfRangeException;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Contains the task list.
 * Includes operations to add/delete duke.tasks in the list.
 */
public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int getLength() {
        return this.taskList.size();
    }

    /**
     * Appends a new task to the TaskList.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param taskNo index of the task to be deleted.
     * @throws TaskOutOfRangeException if taskNo is out of range.
     */
    public void deleteTask(int taskNo) throws TaskOutOfRangeException {
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new TaskOutOfRangeException(taskList.size());
        } else {
            taskList.remove(taskNo - 1);
        }
    }

    /**
     * Marks a task in the TaskList as done.
     * @param taskNo index of the task to be marked as done.
     * @throws TaskOutOfRangeException if taskNo is out of range.
     */
    public void taskDone(int taskNo) throws TaskOutOfRangeException {
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new TaskOutOfRangeException(taskList.size());
        } else {
            Task task = this.taskList.get(taskNo - 1);
            task.taskDone();
            taskList.set(taskNo - 1, task);
        }
    }
}
