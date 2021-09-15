package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * TaskList store a list of tasks.
 */
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
        assert taskNumber >= 1;

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
        assert taskNumber >= 1;

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
        assert taskNumber >= 1;

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
    public int getTotalTask() {
        return this.taskList.size();
    }

    /**
     * Returns string format of all tasks to be stored in the file.
     *
     * @return String format of all tasks in the task list.
     */
    public String toFileString() {
        String fileTask = "";

        for (int i = 0; i < taskList.size(); i++) {
            fileTask = fileTask + taskList.get(i).toFileString() + "\n";
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
    public ArrayList<Task> findTask(String keyWord) throws DukeException {
        ArrayList<Task> newTasks = new ArrayList<>();
        int numTask = this.getTotalTask();

        for (int i = 1; i <= numTask; i++) {
            Task task = this.getTask(i);

            boolean isKeyWordPresent = task.isKeyWordPresent(keyWord);
            boolean isTagPresent = task.isTagPresent(keyWord);
            boolean isWantedTask = isKeyWordPresent || isTagPresent;

            if (isWantedTask) {
                newTasks.add(task);
            }
        }

        return newTasks;
    }

    /**
     * Adds tag to the task.
     *
     * @param tag Tag to be added.
     * @param taskNumber Task number of the task to be tagged.
     * @throws DukeException If task get method has an error.
     */
    public void addTag(String tag, int taskNumber) throws DukeException {
        assert taskNumber >= 1;

        try {
            this.getTask(taskNumber).addTag(tag);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number.");
        }
    }

}
