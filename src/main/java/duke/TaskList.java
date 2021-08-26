package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.DukeFileException;
import duke.exceptions.EmptyListException;
import duke.exceptions.TaskIsCompleteException;
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
     * Prints out the contents of all the tasks in the ArrayList of task.
     *
     * @param ui
     * @throws EmptyListException
     */
    public void printTasks(Ui ui) throws EmptyListException {
        if (this.tasks.size() <= 0) {
            throw new EmptyListException();
        }
        ui.printList(this.tasks);
    }


    /**
     * Searches the ArrayList of Task for tasks with the specified keyword and uses an Ui to print it out.
     *
     * @param ui An Ui instance that prints the list of task containing the keyword.
     * @param keyword A String representing the keyword.
     * @throws EmptyListException An exception thrown when the ArrayList of Task is empty.
     */
    public void printFindTasks(Ui ui, String keyword) throws EmptyListException, TaskNotFoundException {
        if (this.tasks.size() <= 0) {
            throw new EmptyListException();
        }
        ArrayList<Task> listOfTaskWithKeyword = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).toString().contains(keyword)) {
                listOfTaskWithKeyword.add(this.tasks.get(i));
            }
        }
        if (listOfTaskWithKeyword.size() == 0) {
            throw new TaskNotFoundException();
        }
        ui.printFindTask(listOfTaskWithKeyword);
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
     * @param ui     An Ui instance that helps to print out the message of this action to the user.
     * @throws TaskIsCompleteException An exception thrown when the task to be mark is already done.
     * @throws DukeFileException    An exception thrown when the store gets an error from storing the action.
     */
    public void markTask(int index, Storage store, Ui ui)
            throws TaskIsCompleteException, DukeFileException {
        try {
            Task t = tasks.get(index);
            if (t.isDone()) {
                throw new TaskIsCompleteException(index + 1);
            } else {
                int indexOnList = index + 1;
                store.appendCommand("done " + indexOnList);
                t.markAsDone();
                ui.printMarkTaskDone(t);
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
     * @param ui    An Ui instance to print the message of this action to the user.
     * @throws DukeFileException  An exception thrown when the store gets an error from storing the action.
     */
    public void deleteTask(int index, Storage store, Ui ui)
            throws DukeFileException {
        try {
            Task t = tasks.get(index);
            int indexOnList = index + 1;
            store.appendCommand("delete " + indexOnList);
            tasks.remove(index);
            ui.printRemoveTask(t, tasks.size());
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

}
