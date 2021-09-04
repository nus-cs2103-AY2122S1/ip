package duke.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import duke.exceptions.CompletedTaskException;
import duke.exceptions.DukeFileException;
import duke.exceptions.EmptyListException;
import duke.exceptions.TaskNotFoundException;
import duke.task.Task;

/**
 * This is a TaskList class that contains the task list.
 */
public class TaskList {

    /**
     * This is the private class fields of a TaskList instance.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructors of TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the current ArrayList of task.
     *
     * @param t A task that is created by the user.
     */
    public void addToList(Task t) {
        tasks.add(t);
    }

    /**
     * Returns a String representing the contents of all the tasks in the ArrayList of task.
     *
     * @param ui An Ui instance.
     * @return A String representing all the task in the list.
     * @throws EmptyListException An exception thrown when the list is empty.
     */
    public String printTasks(Ui ui) throws EmptyListException {
        if (this.tasks.size() <= 0) {
            throw new EmptyListException();
        }
        return ui.showList(this.tasks);
    }


    /**
     * Searches the ArrayList of Task for tasks with the specified keyword and returns a String from Ui.
     *
     * @param ui An Ui instance that prints the list of task containing the keyword.
     * @param keyword A String representing the keyword.
     * @return A String representing all tasks found.
     * @throws EmptyListException An exception thrown when the ArrayList of Task is empty.
     */
    public String printFindTasks(Ui ui, String keyword) throws EmptyListException, TaskNotFoundException {
        if (this.tasks.size() <= 0) {
            throw new EmptyListException();
        }
        ArrayList<Task> listOfTaskWithKeyword = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getDescription().contains(keyword)) {
                listOfTaskWithKeyword.add(this.tasks.get(i));
            }
        }
        if (listOfTaskWithKeyword.size() == 0) {
            throw new TaskNotFoundException();
        }
        return ui.showTasksWithKeyword(listOfTaskWithKeyword);
    }

    /**
     * Saves the current ArrayList of task using a Storage instance.
     *
     * @param store  A Storage instance that helps to save the task list in a text file.
     * @throws DukeFileException An exception thrown when store encounters an error when storing the tasks.
     */
    public void safeTasks(Storage store) throws DukeFileException {
        try {
            store.safeFile(this.tasks);
        } catch (IOException e) {
            throw new DukeFileException();
        }
    }

    /**
     * Marks a task in the ArrayList of Task as done.
     *
     * @param index  An int representing the index of task to be marked.
     * @param store  A Storage instance to save this action into the text file.
     * @return A Task instance that represents the task marked as done.
     * @throws CompletedTaskException An exception thrown when the task to be mark is already done.
     * @throws DukeFileException    An exception thrown when the store gets an error from storing the action.
     */
    public Task markTask(int index, Storage store)
            throws CompletedTaskException, DukeFileException {
        try {
            assert index < tasks.size() : "Index of Task Out of Bounds!";
            Task taskDone = tasks.get(index);
            if (taskDone.isDone()) {
                throw new CompletedTaskException(index + 1);
            } else {
                int indexOnList = index + 1;
                store.appendCommand("done " + indexOnList);
                taskDone.markAsDone();
                return taskDone;
            }
        } catch (IOException e) {
            throw new DukeFileException();
        }
    }

    /**
     * Deletes a task from the ArrayList of Task.
     *
     * @param index An int representing the index of the task to be deleted.
     * @param store A Storage instance to save this action into a text file.
     * @return A Task instance that represents the deleted Task.
     * @throws DukeFileException  An exception thrown when the store gets an error from storing the action.
     */
    public Task deleteTask(int index, Storage store)
            throws DukeFileException {
        try {
            assert index < tasks.size() : "Index of Task Out of Bounds!";
            Task deletedTask = tasks.get(index);
            int indexOnList = index + 1;
            store.appendCommand("delete " + indexOnList);
            tasks.remove(index);
            return deletedTask;
        } catch (IOException e) {
            throw new DukeFileException();
        }
    }

    /**
     * Returns an int representing the number of Task in the list.
     *
     * @return An int representing the number of Tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Sorts the ArrayList of Tasks.
     */
    public void sortList() {
        Collections.sort(this.tasks);
    }

}
