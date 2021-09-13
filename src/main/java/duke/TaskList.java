package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The items in the bot.
 * Responsible for adding things to the list of items.
 */
public class TaskList {

    /**
     * items to be stored in the list.
     */
    private ArrayList<Task> tasks;

    private Storage storage;

    /**
     * Instantiates an Items object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        this.storage = new Storage("./data", "duke.txt");
    }

    /**
     * Constructor for Items.
     *
     * @param tasks An ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Adds an item to the list.
     *
     * @param task A task to represent the item added.
     * @return A status message to be displayed.
     */
    public String addItem(Task task) throws DukeException {
        tasks.add(task);
        String output = "Got it, I've added this task:\n" + task.toString();
        if (tasks.size() == 1) {
            output += "\nNow you have 1 task in the list.";
        } else {
            output += "\nNow you have " + getListSize() + " tasks in the list.";
        }
        storage.addToFile(task.toString());
        return output;
    }

    /**
     * Marks the specified task as done.
     *
     * @param index the index at which the task is.
     * @return error message if index is greater than the length of list, else completion message.
     */
    public String markDone(int index) throws DukeException {
        System.out.println("Reached tasklist. index is: " + index);
        if (index <= 0) {
            throw new DukeException("Invalid index. Only non-negative values are accepted.");
        }
        if (tasks.size() == 0) {
            throw new DukeException("You have 0 tasks. Add some tasks first.");
        }
        if (index > tasks.size()) {
            throw new DukeException("You don't have these many tasks!");
        }
        int taskIndex = index - 1;
        Task task = tasks.get(taskIndex);
        storage.markTaskDone(taskIndex);
        return task.doneTask();
    }

    /**
     * Marks the specified task as not done.
     *
     * @param index the index at which the task is.
     * @return string representation of changed task
     */
    public String markUndone(int index) throws DukeException {
        Task task = tasks.get(index - 1);
        task.undoTask();
        storage.markTaskUndone(index - 1);
        return task.toString();
    }

    /**
     * Deletes the item at the specified index.
     *
     * @param index index at which item is to be deleted.
     * @return output message stating item has been deleted.
     * @throws DukeException thrown in case of a wrong input.
     */
    public String deleteItem(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("Invalid index. Only positive values are accepted.");
        }
        if (getListSize() == 0) {
            throw new DukeException("You have 0 tasks. Add some tasks first.");
        }
        if (index > getListSize()) {
            throw new DukeException("You don't have these many tasks!");
        }
        int listIndex = index - 1;
        Task task = tasks.get(listIndex);
        tasks.remove(listIndex);
        storage.deleteFromFile(listIndex);
        DukeConstants.deleteTask = task.toString();
        String output =  "Noted. I have removed this task:\n" + task
                + "\n Number of tasks remaining: " + getListSize();
        return output;

    }

    /**
     * Generates the String representation of the items object.
     *
     * @return The String representation of the items object.
     */
    public String printList() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("You have 0 items in your list");
        }
        StringBuilder output = new StringBuilder("These are your tasks: \n");
        System.out.println(getTaskAtIndex(0));
        for (int i = 0; i < getListSize(); i++) {
            if (i < getListSize() - 1) {
                output.append(" ").append(i + 1).append(". ").append(getTaskAtIndex(i)).append("\n");
            } else {
                output.append(" ").append(i + 1).append(". ").append(tasks.get(i).toString());
            }
        }

        assert !output.toString().equals("") : "Unforseen error: Unable to print the tasks. Please try again later.";
        return output.toString();
    }

    /**
     * Finds the tasks with the given keyword.
     *
     * @param keyword the word to be searched for in the list.
     * @return string containing all tasks with the keyword.
     * @throws DukeException thrown if the task list is empty.
     */
    public String findTask(String keyword) throws DukeException {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list: ");
        int ctr = 0;
        for (Task task : tasks) {
            String[] splitString = task.toString().split("\\s");
            for (String word : splitString) {
                if (word.equals(keyword)) {
                    output.append("\n").append(ctr + 1).append(". ").append(task);
                    ctr++;
                }
            }
        }
        if (ctr == 0) {
            throw new DukeException("Sorry, your keyword didn't match anything :/");
        }
        return output.toString();
    }

    /**
     * Deletes the last added task in the list.
     * Used for the undo functionality.
     *
     * @return output message stating the last task has been deleted.
     */
    public String deleteLatestTask() throws DukeException {
        int lastIndex = getListSize() - 1;
        Task task = tasks.get(lastIndex);
        tasks.remove(lastIndex);
        storage.deleteFromFile(lastIndex);
        return task.toString();
    }

    /**
     * Adds a recently deleted task back to the task list.
     * Implements undo functionality
     *
     * @param index index at which task will be added
     * @param task task to be added
     * @return output after adding task
     */
    public String addDeletedTask(int index, Task task) throws DukeException {
        String fileTask = task.toString();
        if ((index - 1) >= getListSize()) {
            tasks.add(task);
            storage.addToFile(fileTask);
        } else {
            int indexToAdd = index - 1;
            tasks.add(indexToAdd, task);
            storage.addToFile(indexToAdd, fileTask);
        }
        String output = "The following task has been re-added at position: " + index;
        return output;
    }

    /**
     * Retrieves the task at given index.
     * Used for undo functionality
     *
     * @param index index at which task exists
     * @return string representation of the task
     */
    public String getTaskAtIndex(int index) {
        Task task = tasks.get(index);
        return task.toString();
    }

    /**
     * Retrieves the current size of the arraylist.
     * Used in undo functionality.
     *
     * @return the size of the array list.
     */
    public int getListSize() {
        return tasks.size();
    }
}
