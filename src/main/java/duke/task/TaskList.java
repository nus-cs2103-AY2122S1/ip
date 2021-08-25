package duke.task;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object with empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the given task list.
     *
     * @param taskList Task list that are stored in previous execution.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds task into the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes task in the task list.
     *
     * @param taskNumber The task number of the task to be deleted.
     * @throws DukeException If task number entered is invalid.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        try {
            this.taskList.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number.");
        }

    }

    /**
     * Returns task based on task number.
     *
     * @param taskNumber The task number of the task to get.
     * @return Task of the task number.
     * @throws DukeException If task number entered is invalid.
     */
    public Task getTask(int taskNumber) throws DukeException {
        try {
            return this.taskList.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number.");
        }

    }

    /**
     * Marks task specified as done.
     *
     * @param taskNumber The task number of the task to mark as done.
     * @throws DukeException If task number entered is invalid.
     */
    public void doneTask(int taskNumber) throws DukeException {
        try {
            this.taskList.get(taskNumber - 1).markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number.");
        }
    }

    /**
     * Returns the number of tasks stored in the task list.
     *
     * @return Number of tasks stored in the task list.
     */
    public int totalTask() {
        return this.taskList.size();
    }

    /**
     * Returns string format of all tasks to be stored in the file.
     *
     * @return String format of all tasks in the task list.
     */
    public String toFileString() {
        String fileTask;

        if (taskList.isEmpty()) {
            fileTask = "";
        } else {
            fileTask = taskList.get(0).toFileString();
        }

        for (int i = 1; i < taskList.size(); i++) {
            fileTask = fileTask + "\n" + taskList.get(i).toFileString();
        }

        return fileTask;
    }

    /**
     * Returns an array of tasks that match the keyword.
     *
     * @param keyWord Key word to match the task.
     * @return Array of tasks that match the keyword
     * @throws DukeException If task get method has an error.
     */
    public ArrayList<Task> findTask(String keyWord) throws DukeException{
        ArrayList<Task> newTasks = new ArrayList<>();

        int numTask = this.totalTask();

        for(int i = 1; i <= numTask; i++) {
            Task task = this.getTask(i);
            if (task.isKeyWordPresent(keyWord)) {
                newTasks.add(task);
            }
        }

        return newTasks;
    }

}
