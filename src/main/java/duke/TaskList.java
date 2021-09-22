package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks processed by Duke.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Task mostRecent;

    /**
     * Class constructor that constructs a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int noOfTask() {
        return this.tasks.size();
    }

    /**
     * Returns a String representation of the TaskList.
     *
     * @return String representation of TaskList.
     */
    @Override
    public String toString() {
        // Precompute information required
        int length = this.tasks.size();
        String list = "";

        // Iterate through all the tasks in the list
        for (int i = 0; i < length; i++) {
            list = list + (i + 1) + ". " + this.tasks.get(i) +"\n";
        }

        return list;
    }

    /**
     * Returns task that is most recently added or deleted.
     *
     * @return Task.
     */
    public Task getMostRecent() {
        return this.mostRecent;
    }

    /**
     * Adds task to the taskList. Assigns this task to be mostRecent.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
        this.mostRecent = task;
    }

    private boolean isZeroOrNegative(int number) {
        return number <= 0;
    }

    /**
     * Deletes task from the taskList. Assigns this task to be mostRecent.
     *
     * @param taskIndex Index number of the task to be removed.
     * @throws DukeException If the user puts in an integer that is less than or equals to 0,
     * or an integer larger than the number of task in the list.
     */
    public void delete(int taskIndex) throws DukeException {
        int length = this.tasks.size();
        if (taskIndex > length || isZeroOrNegative(taskIndex)) {
            throw new DukeException("you have to choose a number based on the list!");
        } else {
            Task removed = this.tasks.get(taskIndex - 1);
            this.mostRecent = removed;

            this.tasks.remove(taskIndex - 1);
        }
    }

    /**
     * Marks the task in the taskList as done. The task is selected based on the index number given.
     *
     * @param taskIndex Integer given to obtain task from taskList.
     * @throws DukeException If the user puts in an integer that is less than or equals to 0,
     * or an integer larger than the number of task in the list.
     */
    public void done(int taskIndex) throws DukeException {
        int length = this.tasks.size();
        if (taskIndex > length || isZeroOrNegative(taskIndex)) {
            throw new DukeException("you have to choose a number based on the list!");
        } else {
            Task task = this.tasks.get(taskIndex - 1);
            task.setDone();
            this.mostRecent = task;
        }
    }

    /**
     * Finds all the tasks in the TaskList that contains the keyword.
     *
     * @param keyword String to be identified within each task.
     * @return TaskList containing tasks that contains the keyword.
     */
    public TaskList find(String keyword) {
        int length = this.tasks.size();
        TaskList taskList = new TaskList();

        for (int i = 0; i < length; i++) {
            Task task = this.tasks.get(i);

            if (task.isKeyword(keyword)) {
                taskList.add(task);
            }
        }

        return taskList;
    }
}
