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
     * Instantiates an Items object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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

    public TaskList(Storage storage) throws DukeException {
        storage.loadData();
        this.storage = storage;
    }

    /**
     * Adds an item to the list.
     *
     * @param task A task to represent the item added.
     * @return A status message to be displayed.
     */
    public String addItem(Task task) throws DukeException {
        ArrayList<Task> currList = Undo.state.get(0);
        currList.add(task);
        String output = "Got it, I've added this task:\n" + task.toString();
        if (currList.size() == 1) {
            output += "\nNow you have 1 task in the list.";
        } else {
            output += "\nNow you have " + getListSize() + " tasks in the list.";
        }
        Undo.state.add(currList);
        return output;
    }

    /**
     * Marks the specified task as done.
     *
     * @param index the index at which the task is.
     * @return error message if index is greater than the length of list, else completion message.
     */
    public String markDone(int index) throws DukeException {
        if (index <= 0) {
            throw new DukeException("Invalid index. Only non-negative values are accepted.");
        }
        ArrayList<Task> currList = Undo.state.get(0);
        if (currList.size() == 0) {
            throw new DukeException("You have 0 tasks. Add some tasks first.");
        }
        if (index > currList.size()) {
            throw new DukeException("You don't have these many tasks!");
        }
        int taskIndex = index - 1;
        ArrayList<Task> newList = Undo.state.get(0);
        Task task = newList.get(taskIndex);
        String output = task.doneTask();
        Undo.state.add(newList);
        return output;
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
        ArrayList<Task> newList = Undo.state.get(0);
        Task task = newList.get(listIndex);
        newList.remove(listIndex);
        Undo.state.add(newList);
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
        System.out.println("reached printlist");
        ArrayList<Task> currList = Undo.state.get(0);

        System.out.println("size = " + currList.size());
        if (currList.size() == 0) {
            throw new DukeException("You have 0 items in your list");
        }
        StringBuilder output = new StringBuilder("These are your tasks: \n");
        for (int i = 0; i < currList.size(); i++) {
            if (i < currList.size() - 1) {
                output.append(" ").append(i + 1).append(". ").append(getTaskAtIndex(i)).append("\n");
            } else {
                output.append(" ").append(i + 1).append(". ").append(getTaskAtIndex(i));
            }
        }

        assert !output.toString().equals("") : "Unforseen error: Unable to print the tasks. Please try again later.";
        return output.toString();
    }

    public static ArrayList<String> getStringList() {
        ArrayList<Task> newList = Undo.state.get(0);
        ArrayList<String> fileList = new ArrayList<>();
        for (Task task : newList) {
            fileList.add(task.toString());
        }
        return fileList;
    }

    /**
     * Finds the tasks with the given keyword.
     *
     * @param keyword the word to be searched for in the list.
     * @return string containing all tasks with the keyword.
     * @throws DukeException thrown if the task list is empty.
     */
    public String findTask(String keyword) throws DukeException {
        ArrayList<Task> newList = Undo.state.get(0);
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list: ");
        int ctr = 0;
        for (Task task : newList) {
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
     * Retrieves the task at given index.
     * Used for undo functionality
     *
     * @param index index at which task exists
     * @return string representation of the task
     */
    public String getTaskAtIndex(int index) {
        ArrayList<Task> newList = Undo.state.get(0);
        Task task = newList.get(index);
        return task.toString();
    }

    /**
     * Retrieves the current size of the arraylist.
     * Used in undo functionality.
     *
     * @return the size of the array list.
     */
    public int getListSize() {
        ArrayList<Task> newList = Undo.state.get(0);
        return newList.size();
    }
}
