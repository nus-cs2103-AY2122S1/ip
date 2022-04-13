package duke.task;

import duke.exception.DukeIncorrectInputs;
import duke.exception.DukeNoSuchTask;
import duke.exception.DukeUnableToFind;

import java.util.ArrayList;

/**
 * Represents a Task List that stores the log of
 * tasks in the users' to-do list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    private TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Returns the total number of tasks in the list in String.
     */
    public String getTotal() {
        int total = this.listOfTasks.size();
        return total + (total == 1 ? " task" : " tasks");
    }

    /**
     * Returns the total number of tasks in the list in String.
     */
    public int getTotalNumber() {
        return this.listOfTasks.size();
    }

    /**
     * Adds a task into the list of tasks.
     *
     * @param task the task to be added into the list of tasks.
     */
    public void addTaskToList(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * Creates a TaskList object.
     *
     * @return a new TaskList that has no tasks stored.
     */
    public static TaskList makeNewTaskList() {
        return new TaskList();
    }

    /**
     * Sets the status of the specified task as 'done'.
     * @param i Index of the task in the task list, that
     *          is to be set as 'done'.
     * @throws DukeIncorrectInputs if the command entered violates given
     */
    public void setTaskAsDone(int i) throws DukeIncorrectInputs {
        if (i < 1 || i > this.listOfTasks.size()) {
            throw new DukeNoSuchTask(new IllegalArgumentException());
        }
        this.listOfTasks.get(i - 1).markAsDone();
    }

    /**
     * Returns the task at the specified position.
     * @param i Index of the task in the task list, that
     *          is to be returned.
     */
    public Task getTask(int i) {
        return this.listOfTasks.get(i);
    }

    /**
     * Removes the specified task from the list of tasks.
     * @param i index of the task to be removed.
     * @return task that is removed.
     * @throws DukeIncorrectInputs if the command entered violates given
     * rules.
     */
    public Task deleteTask(int i) throws DukeIncorrectInputs {
        if (i < 1 || i > this.listOfTasks.size()) {
            throw new DukeNoSuchTask(new IllegalArgumentException());
        }
        return this.listOfTasks.remove(i - 1);
    }

    /**
     * Finds the tasks based on the keyword from the user.
     * @param keyword keyword from user.
     * @return all tasks that have the keyword.
     * @throws DukeIncorrectInputs if the command entered violates given
     * rules.
     */
    public TaskList findTasks(String keyword) throws DukeIncorrectInputs {
        int len = this.listOfTasks.size();
        TaskList tasksWithKey = new TaskList();

        for (int i = 0; i < len; i++) {
            if (this.listOfTasks.get(i).task.toLowerCase().contains(keyword)) {
                tasksWithKey.listOfTasks.add(this.listOfTasks.get(i));
            }
        }

        if (tasksWithKey.getTotalNumber() == 0) {
            throw new DukeUnableToFind();
        }

        return tasksWithKey;
    }

    /**
     * Represents TaskList object in string.
     */
    @Override
    public String toString() {
        if (this.listOfTasks.size() == 0) { // if there is no task within list
            return "\tYou have not added any tasks to your list.\n"
                    + "\tLog any task you wish to add.\n";
        }
        int i = 1;
        String toPrint = "";

        for (Task task : this.listOfTasks) {
            toPrint = toPrint + "\t" + i + ". " + task + "\n";
            i++;
        }
        return toPrint;
    }

    /**
     * Sorts tasks by their deadline or event occurring time.
     */
    public void sortListOfTasks() {
        listOfTasks.sort(Task::compareTo);
    }
}
