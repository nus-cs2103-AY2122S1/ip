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
     * Instantiates a tasklise object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        this.storage = new Storage("./data", "duke.txt");
    }

    /**
     * Instantiates a TaskList object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.storage = new Storage("./data", "duke.txt");
    }

    /**
     * Constructor for TaskList.
     *
     * @param tasks An ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Wraps a given arrayList of tasks in a tasklist.
     *
     * @param tasks the arraylist of tasks.
     * @return a tasklist containing the arraylist of tasks.
     */
    public static TaskList of(ArrayList<Task> tasks) {
        return new TaskList(tasks);
    }

    public TaskList(Storage storage) throws DukeException {
        storage.loadData();
        this.storage = storage;
    }

    /**
     * Creates a copy of a given tasklist.
     *
     * Used to implement undo
     * @param taskList the list who's copy is to be made
     * @return copied tasklist
     */
    private TaskList copyTasklist(TaskList taskList) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task : taskList.tasks) {
            newList.add(task);
        }
        return TaskList.of(newList);
    }

    /**
     * Adds an item to the list.
     *
     * @param task A task to represent the item added.
     * @return A status message to be displayed.
     */
    public String addItem(Task task) throws DukeException {
        TaskList currList = Duke.getLatestState();
        TaskList newList = copyTasklist(currList);
        newList.tasks.add(task);
        String output = "Got it, I've added this task:\n" + task.toString();
        if (newList.getListSize() == 0) {
            output += "\nNow you have 1 task in the list.";
        } else {
            output += "\nNow you have " + newList.getListSize() + " tasks in the list.";
        }
        Duke.addToState(newList);
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
        TaskList currList = copyTasklist(Duke.getLatestState());
        if (currList.getListSize() == 0) {
            throw new DukeException("You have 0 tasks. Add some tasks first.");
        }
        if (index > currList.getListSize()) {
            throw new DukeException("You don't have these many tasks!");
        }
        int taskIndex = index - 1;
        Task task = currList.getTaskAtIndex(taskIndex);
        String output = task.doneTask();
        Duke.addToState(currList);
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
        TaskList newList = copyTasklist(Duke.getLatestState());
        Task task = newList.getTaskAtIndex(listIndex);
        newList.tasks.remove(listIndex);
        Duke.addToState(newList);
        String output =  "Noted. I have removed this task:\n" + task
                + "\n Number of tasks remaining: " + newList.getListSize();
        return output;

    }

    /**
     * Generates the String representation of the items object.
     *
     * @return The String representation of the items object.
     */
    public String printList() throws DukeException {
        int listSize = this.getListSize();
        if (listSize == 0) {
            throw new DukeException("You have 0 items in your list");
        }
        StringBuilder output = new StringBuilder("These are your tasks: \n");
        for (int i = 0; i < listSize; i++) {
            if (i < listSize - 1) {
                output.append(" ").append(i + 1).append(". ").append(this.getTaskAtIndex(i)).append("\n");
            } else {
                output.append(" ").append(i + 1).append(". ").append(this.getTaskAtIndex(i));
            }
        }

        assert !output.toString().equals("") : "Unforseen error: Unable to print the tasks. Please try again later.";
        return output.toString();
    }

    /**
     * Returns an arraylist containing all tasks in toString() format.
     *
     * @return an arraylist of strings.
     */
    public static ArrayList<String> getStringList() {
        TaskList newList = Duke.getLatestState();
        ArrayList<String> fileList = new ArrayList<>();
        for (Task task : newList.tasks) {
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
        TaskList newList = Duke.getLatestState();
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list: ");
        int ctr = 0;
        for (Task task : newList.tasks) {
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
    public Task getTaskAtIndex(int index) {
        TaskList newList = Duke.getLatestState();
        Task task = newList.tasks.get(index);
        return task;
    }

    /**
     * Retrieves the current size of the arraylist.
     * Used in undo functionality.
     *
     * @return the size of the array list.
     */
    public int getListSize() {
        return this.tasks.size();
    }
}
