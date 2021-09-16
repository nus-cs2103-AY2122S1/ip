package duke;

import java.util.ArrayList;

/**
 * Represents a user's todo List.
 */
public class TaskList {

    private ArrayList<Task> taskList;
    private int numOfTask;

    TaskList() {
        this.taskList = new ArrayList<Task>();
        this.numOfTask = 0;
    }

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.numOfTask = taskList.size();
    }

    /**
     * Returns the user's todo list.
     *
     * @return The user's todo list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }


    /**
     * Marks the task corresponding to the given task number as done and returns the task marked
     * as done.
     *
     * @param taskNumber The task number of the task that should be marked as done.
     *                   The first task in the user's todo list have the task number of 1,
     *                   the second task in the user's todo list have the task number of 2,
     *                   etc.
     * @return The task marked as done.
     * @throws DukeException If no task in the user's todo list corresponds to the given task number.
     */
    public Task markTaskAsDone(int taskNumber) throws DukeException {
        int index = taskNumber - 1;
        checkCanDeleteOrMarkAsDone(taskNumber, index);
        this.taskList.get(index).markAsDone();
        return this.taskList.get(index);
    }

    /**
     * Adds the given task to the end of the user's todo list.
     *
     * @param task The task to be added to the user's todo list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        this.numOfTask = this.numOfTask + 1;
    }


    /**
     * Deletes the task corresponding to the given task number and returns the task deleted.
     *
     * @param taskNumber The task number of the task that should be deleted.
     *                   The first task in the user's todo list have the task number of 1,
     *                   the second task in the user's todo list have the task number of 2,
     *                   etc.
     * @return The deleted task.
     * @throws DukeException If no task in the user's todo list corresponds to the given task number.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        int index = taskNumber - 1;
        checkCanDeleteOrMarkAsDone(taskNumber, index);
        Task task = this.taskList.get(index);
        this.taskList.remove(index);
        this.numOfTask = this.numOfTask - 1;
        return task;
    }


    private void checkCanDeleteOrMarkAsDone(int taskNumber, int index) throws DukeException {
        if (index > this.numOfTask - 1) {
            if (this.numOfTask > 1) {
                throw new DukeException(String.format("Cannot find task %s." +
                        "There are only %s tasks in your list.", taskNumber, this.numOfTask));
            } else if (this.numOfTask == 1) {
                throw new DukeException(String.format("Cannot find task %s." +
                        "There is only %s task in your list.", taskNumber, this.numOfTask));
            } else {
                throw new DukeException(String.format("Cannot find task %s." +
                        "There is no task in your list.", taskNumber));
            }
        } else if (index < 0) {
            throw new DukeException(String.format("There is no task %s.", taskNumber));
        }
    }

    /**
     * Returns an ArrayList of Task objects containing the given search keyword.
     *
     * @param str Keyword used for searching.
     * @return ArrayList of Task objects matching the keyword.
     */
    public ArrayList<Task> findTask(String str) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t: this.taskList) {
            if (t.getDescription().contains(str)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Get the number of tasks in todo list.
     *
     * @return the number of tasks in todo list.
     */
    public int getNumOfTask() {
        return this.numOfTask;
    }

}
