package duke;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.*;

/**
 * Container class for the lists of tasks
 */

public class TaskList {
    private ArrayList<Task> listOfTasks;

    TaskList(List<Task> tasksFromStorage) {
        listOfTasks = new ArrayList<>();
        if (!tasksFromStorage.isEmpty()) {
            listOfTasks.addAll(tasksFromStorage);
        }
    }

    TaskList() {
        listOfTasks = new ArrayList<>();
    }

    /** Helper function to get the number of tasks in the list */
    public int getNumTasks() {
        return listOfTasks.size();
    }

    /** Helper function to view the tasks in the list */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /** Helper function to get the add a new task to the list */
    public void addTask(Task t) {
        listOfTasks.add(t);
    }

    /**
     * Function to delete the specified task number from the list.
     * Logic and extent of list modification is subjected to the following conditions:
     * Condition 1: Only one task in the list
     * Condition 2: Specified task is the first in the list
     * Condition 3: Specified task is the last in the list
     * Condition 4: Specified task is not the first/last task
     */
    public String deleteTask(int deleteIndex) {

        assert (deleteIndex >= 0 && deleteIndex <= listOfTasks.size()) : "Invalid index";

        String deletedTask = listOfTasks.get(deleteIndex - 1).toString();
        if (this.listOfTasks.size() == 1) {
            this.listOfTasks.clear();
        } else if (deleteIndex == 1) {
            this.listOfTasks = new ArrayList<>(listOfTasks.subList(1, listOfTasks.size()));
        } else if (deleteIndex == listOfTasks.size()) {
            this.listOfTasks = new ArrayList<>(listOfTasks.subList(0, listOfTasks.size() - 1));
        } else {
            ArrayList<Task> newList = new ArrayList<>(this.listOfTasks.subList(0, deleteIndex - 1));
            for (int i = deleteIndex; i < listOfTasks.size(); i++) {
                newList.add(listOfTasks.get(i));
            }
            this.listOfTasks = newList;
        }
        return deletedTask;
    }

    /**
     * Function to 'complete' the specified task in the list based on the task type.
     * Returns the toString() value of the task for Ui class to output.
     */
    public String completeTask(int taskIndex) {

        assert (taskIndex >= 0 && taskIndex <= listOfTasks.size()) : "Invalid index";

        String taskInfo;
        if (this.listOfTasks.get(taskIndex - 1) instanceof Todo) {
            Todo completedTodo = ((Todo) this.listOfTasks.get(taskIndex - 1)).markAsDone();
            this.listOfTasks.set(taskIndex - 1, completedTodo);
            taskInfo = completedTodo.toString();
        } else if (this.listOfTasks.get(taskIndex - 1) instanceof Deadline) {
            Deadline completedDeadline = ((Deadline) this.listOfTasks.get(taskIndex - 1)).markAsDone();
            this.listOfTasks.set(taskIndex - 1, completedDeadline);
            taskInfo = completedDeadline.toString();
        } else if (this.listOfTasks.get(taskIndex - 1) instanceof DoWithinPeriod) {
            DoWithinPeriod completedDoWithin = ((DoWithinPeriod) this.listOfTasks.get(taskIndex - 1)).markAsDone();
            this.listOfTasks.set(taskIndex - 1, completedDoWithin);
            taskInfo = completedDoWithin.toString();
        } else {
            Event completedEvent = ((Event) this.listOfTasks.get(taskIndex - 1)).markAsDone();
            this.listOfTasks.set(taskIndex - 1, completedEvent);
            taskInfo = completedEvent.toString();
        }
        return taskInfo;
    }

    /**
     * Finds a lists of tasks which contains the given keyword within the task (if any).
     * Else, an empty list would be returned
     *
     * @param keyWord input string in question
     * @return a lists of tasks of size >= 0
     */
    public ArrayList<Task> findMatching(String keyWord) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t: listOfTasks) {
            if (t.toString().contains(keyWord)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }

    /** Helper function to check if tasklist is empty */
    public boolean isEmptyTaskList() {
        return listOfTasks.isEmpty();
    }

}
