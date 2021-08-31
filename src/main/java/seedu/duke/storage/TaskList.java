package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.commands.Ui;
import seedu.duke.exceptions.action.DukeActionOutOfBoundException;
import seedu.duke.tasks.Task;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Primary Constructor.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Retrieves the list of tasks that is being stored in this class.
     * 
     * @return an ArrayList of tasks which is being stored.
     * @see java.util.ArrayList
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Loads previous saved Tasks from a txt file. Save it in this current TaskList
     * in an ArrayList of Task object.
     * 
     * @param savedTasks this is the ArrayList of Task coming from the txt file.
     * @see java.util.ArrayList
     */
    public void loadFromStorage(ArrayList<Task> savedTasks) {
        this.taskList.addAll(savedTasks);
    }

    /**
     * Helps to mark the task in the list of Tasks as done.
     * 
     * @param number is the Task id.
     */
    public void doneItem(int index) {
        try {
            Task curr = this.taskList.get(index);
            this.taskList.set(index, curr.markAsDone());

        } catch (IndexOutOfBoundsException err) {
            throw new DukeActionOutOfBoundException(Ui.ERROR_MESSAGE_ACTION_OUT_OF_BOUND);
        }
    }

    /**
     * Adds a particular task given by the user into the TaskList.
     * 
     * @param task is the task that is to be added into the TaskList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a particular task that is present in the TaskList.
     * 
     * @param number is the Task id.
     */
    public Task deleteItem(int index) {
        try {
            return this.taskList.remove(index);
        } catch (IndexOutOfBoundsException err) {
            throw new DukeActionOutOfBoundException(Ui.ERROR_MESSAGE_ACTION_OUT_OF_BOUND);
        }
    }

    /**
     * Helps to find the specific Task from the list based on the description given.
     * 
     * @param find is the description coming from the user, which the list of Tasks
     *             they would like to find.
     */
    public ArrayList<Task> find(String find) {
        ArrayList<Task> foundList = new ArrayList<>();

        this.taskList.forEach(task -> {
            if (task.getDescription().contains(find)) {
                foundList.add(task);
            }
        });

        return foundList;

    }
}
