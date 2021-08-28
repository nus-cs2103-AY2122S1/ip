package duke;

import java.util.ArrayList;
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
    /**
     * Adds a task to the list of tasks.
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskId The task identifier.
     * @throws DukeException.MissingTaskException The exception is thrown when the user tries to delete a non-existing task.
     */
    public void deleteTask(int taskId) throws DukeException.MissingTaskException {
        try {
            Task t = taskList.get(taskId-1);
            taskList.remove(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.MissingTaskException();
        }
    }
}
