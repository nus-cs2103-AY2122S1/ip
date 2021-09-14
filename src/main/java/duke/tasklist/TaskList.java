package duke.tasklist;

import java.util.ArrayList;
import java.util.List;

import duke.exception.IncorrectIndexException;
import duke.exception.TimedTaskDateInputException;
import duke.task.Task;

/**
 * A class which stores the list of tasks used by Duke
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * A constructor method which initialises the TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return the number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the Task at the nth position
     * @param taskId the id of the task
     * @return The task in that position
     * @throws IncorrectIndexException if an invalid index is input
     */
    public Task get(int taskId) throws IncorrectIndexException {
        try {
            return this.tasks.get(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectIndexException();
        }

    }

    /**
     * Adds a task to the list
     * @param task task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task with the taskId from the list and returns it.
     * @param taskId The task to be removed.
     * @return The task which was removed.
     * @throws IncorrectIndexException if the taskId is invalid.
     */
    public Task deleteTask(int taskId) throws IncorrectIndexException {
        try {
            return this.tasks.remove(taskId - 1);
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new IncorrectIndexException();
        }
    }

    /**
     *
     * @param task the new task to be placed in the right position.
     * @param taskId the position of the task to be replaced
     * @return the previous task which was replaced by the new task
     * @throws IncorrectIndexException if the taskId was invalid
     */
    public Task updateTask(Task task, int taskId) throws IncorrectIndexException {
        Task taskRetrieved;
        try {
            taskRetrieved = tasks.get(taskId - 1);
            tasks.set(taskId - 1, task);
            return taskRetrieved;
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new IncorrectIndexException();
        }
    }

    /**
     * Marks and returns a completed task.
     * @param taskId of the task that is to be completed.
     * @return The task which was completed.
     * @throws IncorrectIndexException if the taskId is invalid.
     * @throws TimedTaskDateInputException if there is an issue with a created task.
     */
    public Task markAsCompleted(int taskId) throws IncorrectIndexException, TimedTaskDateInputException {
        try {
            Task currentTask = this.tasks.get(taskId - 1);
            Task completedTask = currentTask.complete();
            this.tasks.set(taskId - 1, completedTask);
            return completedTask;
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new IncorrectIndexException();
        }
    }

    /**
     * Returns a filtered TaskList based on a String input.
     * @param input the parameter to filter the list on.
     * @return A TaskList which contains only the filtered tasks.
     */
    public TaskList filter(String input) {
        TaskList taskList = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            Task task = this.tasks.get(i);
            String nameOfTask = task.getName();
            if (nameOfTask.contains(input)) {
                taskList.addTask(task);
            }
        }
        return taskList;
    }

    /**
     * Returns the string representation of the TaskList.
     * @return a string which contains the numbered tasks.
     */
    @Override()
    public String toString() {
        StringBuilder result = new StringBuilder();
        int indexOfLastElement = this.size() - 1;
        for (int j = 0; j <= indexOfLastElement; j++) {
            int correctIdNumber = j + 1;
            String line = correctIdNumber + "." + tasks.get(j).toString();
            result.append(line);
            if (j < indexOfLastElement) {
                result.append("\n");
            }
        }
        return result.toString();
    }

}
