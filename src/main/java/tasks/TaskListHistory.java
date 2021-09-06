package tasks;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Stores the history of the the TaskList as the user changes it while using
 * duke. This allows the user to undo any of his previous command and revert
 * the TaskList to the previous iteration.
 *
 * The history of the TaskList will be stored as a double ended queue in the
 * form of a Java Linked List. The Linked List will only store the 10 previous
 * histories of the Task List. Hence, the user will only be able to undo
 * 10 of his commands.
 */
public class TaskListHistory {

    /** A linked list storing the past versions of the taskList. Note that this
     * only stores a shallow copy of the previous taskList. Hence, it can only
     * keep track of added and deleted tasks. It cannot keep track of any changes
     * made to an individual tasks since they will share the same object reference.
     * The other linked list below is to track changes to a individual task instead.*/
    private final LinkedList<TaskList> taskListVersions = new LinkedList<>();

    /**
     * Since a user can only mark a task as done for now, this linked list keeps
     * track of what tasks are marked as done in a command.
     */
    private final LinkedList<ArrayList<Integer>> taskStateChanges = new LinkedList<>();

    /** A counter of the number of items in the linked list */
    private int count = 0;

    /**
     * Add a new taskList change to the taskList history. If the taskList change does not
     * involve changing the internal state of an individual task, null will be added to the
     * taskStateChanges linked list.
     *
     * @param taskList The taskList to be added.
     * @param stateChanges If any changes were made to an individual tasks.
     */
    public void addTaskListChanges(TaskList taskList, boolean stateChanges) {
        assert taskList != null : "An error occurred with the taskList";
        if (this.count >= 10) {
            assert this.taskListVersions.peekFirst() != null : "An error occurred in taskListHistory";
            this.taskListVersions.poll();
            this.taskStateChanges.poll();
            this.count--;
        }
        this.taskListVersions.addLast(taskList);
        this.count++;
        if (!stateChanges) {
            this.taskStateChanges.addLast(null);
        }
    }

    /**
     * Adds the internal changes made to an individual task. This is only used when a task
     * is marked as done.
     *
     * @param changes An arrayList containing the indexes of tasks in the taskList that was mark as done.
     */
    public void addTaskStateChanges(ArrayList<Integer> changes) {
        assert changes != null : "An error occurred while executing a command";
        this.taskStateChanges.addLast(changes);
    }

    /**
     * Checks if the taskList history has a previous version that can be reverted to.
     *
     * @return True if the taskList history contains a previous version, false otherwise.
     */
    public boolean hasPreviousHistory() {
        return this.taskListVersions.peekLast() != null;
    }

    /**
     * Reverts the taskList to a previous version. If there were changes made to the internal state
     * of the tasks in the taskList, it will revert back the changes based on the taskStateChanges
     * linked list.
     *
     * @return The new current taskList.
     */
    public TaskList revertToPrevious() {
        TaskList previousTaskList = this.taskListVersions.pollLast();
        ArrayList<Integer> previousStates = this.taskStateChanges.pollLast();
        assert previousTaskList != null : "An error occurred while trying to undo";
        this.count--;

        if (previousStates != null) {
            previousTaskList.changeTaskState(previousStates);
        }
        return previousTaskList;
    }
}
