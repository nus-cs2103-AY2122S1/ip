package duke.task;

import duke.exception.TaskAlreadyDoneException;
import duke.exception.TaskIndexOutOfBoundsException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskId The id of the task to be marked as done.
     * @return The task that was marked as done.
     * @throws TaskIndexOutOfBoundsException If the task index is out of bounds.
     * @throws TaskAlreadyDoneException If the task has previously been marked as done.
     */
    public Task markAsDone(int taskId) throws TaskIndexOutOfBoundsException, TaskAlreadyDoneException {
        if (taskId < 0 || (taskId + 1) > taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        Task specifiedTask = taskList.get(taskId);
        if (specifiedTask.getStatusIcon().equals("X")) {
            throw new TaskAlreadyDoneException();
        }
        specifiedTask.markAsDone();
        return specifiedTask;
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param taskId The id of the task to be deleted.
     * @return The task that was deleted.
     * @throws TaskIndexOutOfBoundsException If the task index is out of bounds.
     */
    public Task deleteTask(int taskId) throws TaskIndexOutOfBoundsException {
        if (taskId < 0 || (taskId + 1) > taskList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        Task removedTask = taskList.remove(taskId);
        return removedTask;
    }

    public void addTask(Task taskToBeAdded) {
        taskList.add(taskToBeAdded);
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    /**
     * Find all the tasks with the matching keyword.
     *
     * @param keyword The term to search for.
     * @return A TaskList with matching tasks.
     */
    public TaskList findTasksWithKeyword(String keyword) {
        ArrayList<Task> tasksWithMatchingKeyword = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                tasksWithMatchingKeyword.add(task);
            }
        }
        return new TaskList(tasksWithMatchingKeyword);
    }

    /**
     * Returns the string representation of a TaskList instance.
     *
     * @return A string representing a TaskList instance.
     */
    @Override
    public String toString() {
        String tasksInOrder = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasksInOrder += (i + 1) + "." + taskList.get(i);
            if (i != taskList.size() - 1) {
                tasksInOrder += "\n";
            }
        }
        return tasksInOrder;
    }
}
