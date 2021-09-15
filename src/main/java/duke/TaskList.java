package duke;

import java.util.ArrayList;

import duke.exceptions.TaskOutOfRangeException;
import duke.tasks.Task;

/**
 * Contains the task list.
 * Includes operations to add/delete duke.tasks in the list.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

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
        }
        assert (taskNo >= 1 && taskNo <= taskList.size());
        taskList.remove(taskNo - 1);
    }

    /**
     * Marks a task in the TaskList as done.
     *
     * @param taskNo index of the task to be marked as done.
     * @throws TaskOutOfRangeException if taskNo is out of range.
     */
    public void taskDone(int taskNo) throws TaskOutOfRangeException {
        if (taskNo < 1 || taskNo > taskList.size()) {
            throw new TaskOutOfRangeException(taskList.size());
        }
        assert (taskNo >= 1 && taskNo <= taskList.size());
        Task task = this.taskList.get(taskNo - 1);
        task.markTaskDone();
        taskList.set(taskNo - 1, task);

    }

    /**
     * Searches for tasks that match the searchDescription.
     * @param searchDescription Search description from user input.
     * @return List of tasks with matching task decriptions.
     */
    public ArrayList<Task> findTask(String searchDescription) {
        ArrayList<String> searchTerms = Parser.parseSearchString(searchDescription.toLowerCase());
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : this.taskList) {
            for (String term : searchTerms) {
                if (task.getDescription().toLowerCase().contains(term)) {
                    results.add(task);
                    break;
                }
            }
        }
        return results;
    }
}
