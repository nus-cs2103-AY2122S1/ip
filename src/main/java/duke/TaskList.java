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
     * Marks the task corresponding to the given task number as done.
     *
     * @param taskNumber The task number of the task that should be marked as done.
     *                   The first task in the user's todo list have the task number of 1,
     *                   the second task in the user's todo list have the task number of 2,
     *                   etc.
     * @throws DukeException If no task in the user's todo list corresponds to the given task number.
     */
    public void markAsDone(int taskNumber) throws DukeException {
        int index = taskNumber - 1;
        checkCanDeleteOrMarkAsDone(taskNumber, index);
        this.taskList.get(index).markAsDone();
        System.out.println();
    }

    /**
     * Adds the given task to the end of the user's todo list.
     *
     * @param task The task to be added to the user's todo list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Got it! I have added this task:");
        System.out.println(this.taskList.get(this.numOfTask));
        this.numOfTask = this.numOfTask + 1;
        if (this.numOfTask > 1) {
            System.out.printf("Now you have %s tasks in your list.\n", this.numOfTask);
        } else {
            System.out.printf("Now you have 1 task in your list.\n");
        }
        System.out.println();
    }


    /**
     * Deletes the task corresponding to the given task number.
     *
     * @param taskNumber The task number of the task that should be deleted.
     *                   The first task in the user's todo list have the task number of 1,
     *                   the second task in the user's todo list have the task number of 2,
     *                   etc.
     * @throws DukeException If no task in the user's todo list corresponds to the given task number.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        int index = taskNumber - 1;
        checkCanDeleteOrMarkAsDone(taskNumber, index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.taskList.get(index));
        this.taskList.remove(index);
        this.numOfTask = this.numOfTask - 1;
        if (this.numOfTask > 1) {
            System.out.printf("You have %s tasks left on your list.\n", this.numOfTask);
        } else {
            System.out.printf("You have %s task left on your list.\n", this.numOfTask);
        }
        System.out.println();
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
     * Returns an ArrayList of Task objects matching the given search keyword.
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

}
