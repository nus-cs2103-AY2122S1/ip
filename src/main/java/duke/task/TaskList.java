package duke.task;

import java.util.ArrayList;

import duke.DukeException;

/**
 * Contains the task list.
 *
 * @author Adam Ho
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getListSize() {
        return taskList.size();
    }

    public Task getTaskFromId(int taskId) throws DukeException.MissingTaskException {
        if (taskId < 1 || taskId > taskList.size()) {
            throw new DukeException.MissingTaskException();
        }
        return taskList.get(taskId - 1);
    }
    /**
     * Adds a task to the list of tasks.
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the list of tasks.

     * @throws DukeException.MissingTaskException The exception is thrown when the user tries to delete a non-existing task.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }
}
