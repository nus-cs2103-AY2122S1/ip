package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * This class represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList that contains all the tasks in the given param.
     *
     * @param tasks ArrayList of Tasks that will be stored in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList with no Tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Prints the tasks in the TaskList.
     */
    public String printTaskList() {
        if (tasks.size() == 0) {
            return "There are no tasks in the list.";
        }

        StringBuilder message = new StringBuilder();
        message.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            message.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return message.toString();
    }

    /**
     * Prints the tasks in the TaskList with different display messages depending on if user is finding tasks.
     *
     * @param isFindCommand True if user is finding tasks. False if user is printing entire task list.
     */
    public String printTaskList(boolean isFindCommand) {
        if (tasks.size() == 0) {
            return "There are no matching tasks in the list.";
        }

        StringBuilder message = new StringBuilder();
        message.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            message.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return message.toString();
    }

    protected int getLength() {
        return tasks.size();
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task Task to be added to the TaskList.
     */
    public void addNewTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a Task with the given taskIndex as done and returns the Task.
     *
     * @param taskIndex Index of the Task to be marked as done.
     * @return Task that was marked as done.
     * @throws DukeException If taskIndex is negative or more than or equal to size of array.
     */
    public Task markTaskDone(int taskIndex) throws DukeException {
        validateTaskIndex(taskIndex);
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a Task and returns it.
     *
     * @param taskIndex Index of Task to be deleted.
     * @return Task that was deleted.
     * @throws DukeException If taskIndex is negative or more than or equal to size of array.
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        validateTaskIndex(taskIndex);
        Task task = tasks.get(taskIndex);
        tasks.remove(task);
        return task;
    }

    /**
     * Returns the String representations of all Tasks in an ArrayList in the format to be stored in a file.
     *
     * @return ArrayList of String representations of all Tasks in TaskList in the format to be stored in a file.
     */
    public ArrayList<String> getTaskStrings() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            taskStrings.add(task.toFileString());
        }
        return taskStrings;
    }

    /**
     * Returns a TaskList containing the Tasks that have the keyword searched for by the user in their descriptions.
     *
     * @param keyword Keyword searched for by users.
     * @return TaskList containing the Tasks that have keyword in their descriptions.
     */
    public TaskList findTasksWithKeyword(String keyword) {
        ArrayList<Task> arrListTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                arrListTasks.add(task);
            }
        }
        return new TaskList(arrListTasks);
    }

    /**
     * Updates task in TaskList at specified index with the updated task.
     *
     * @param taskIndex Index of Task to be updated in tasks ArrayList.
     * @param updatedTask Updated Task to replace original Task.
     */
    public void editTask(int taskIndex, Task updatedTask) {
        tasks.set(taskIndex, updatedTask);
    }

    private void validateTaskIndex(int taskIndex) throws DukeException {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            throw new DukeException("There are no tasks in the list.");
        } else if (taskIndex >= taskCount) {
            throw new DukeException("Invalid task number. There are only " + taskCount + "tasks in the list");
        }
        assert taskIndex >= 0 && taskIndex < taskCount : "Invalid task index";
    }

    /**
     * Returns true if object being compared is a TaskList that contains the same Tasks.
     *
     * @param obj Object to be compared to TaskList.
     * @return true if object is equal to TaskList.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            TaskList other = (TaskList) obj;
            return tasks.equals(other.tasks);
        }
        return false;
    }

    /**
     * Returns Task in TaskList at specified index.
     *
     * @param taskIndex Index of Task in tasks ArrayList to be returned.
     * @return Task in TaskList at specified index.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }
}
