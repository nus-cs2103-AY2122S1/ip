package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The items in the bot.
 * Responsible for adding things to the list of items.
 */

public class Items {

    /**
     * items to be stored in the list.
     */
    private ArrayList<Task> tasks;

    /**
     * Instantiates an Items object.
     */
    public Items() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for Items.
     *
     * @param tasks An ArrayList of Tasks
     */
    public Items(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds an item to the list.
     *
     * @param task A task to represent the item added
     * @return A status message to be displayed
     */
    public String addItem(Task task) {
        tasks.add(task);
        String output = "Got it, I've added this task:\n" + task.toString();
        if (tasks.size() == 1) {
            output += "\nNow you have 1 task in the list.";
        } else {
            output += "\nNow you have " + tasks.size() + " tasks in the list.";
        }
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
        if (tasks.size() == 0) {
            throw new DukeException("You have 0 tasks. Add some tasks first.");
        }
        if (index > tasks.size()) {
            throw new DukeException("You don't have these many tasks!");
        }
        Task task = tasks.get(index - 1);
        return task.doneTask();
    }

    /**
     * Deletes the item at the specified index.
     *
     * @param index index at which item is to be deleted
     * @return output message stating item has been deleted
     * @throws DukeException thrown in case of a wrong input
     */
    public String deleteItem(int index) throws DukeException {
        if (index < 0) {
            throw new DukeException("Invalid index. Only positive values are accepted.");
        }
        if (tasks.size() == 0) {
            throw new DukeException("You have 0 tasks. Add some tasks first.");
        }
        if (index > tasks.size()) {
            throw new DukeException("You don't have these many tasks!");
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return "Noted. I have removed this task:\n" + task.toString()
                + "\n Number of tasks remaining: " + tasks.size();

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
        StringBuilder str = new StringBuilder("These are your tasks: \n");

        for (int i = 0; i < tasks.size(); i++) {
            if (i < tasks.size() - 1) {
                str.append(" ").append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
            } else {
                str.append(" ").append(i + 1).append(". ").append(tasks.get(i).toString());
            }
        }
        return str.toString();
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
                    output.append("\n").append(ctr + 1).append(". ").append(task.toString());
                    ctr++;
                }
            }
        }
        if (ctr == 0) {
            throw new DukeException("Sorry, seems like your keyword didn't match anything :/");
        }
        return output.toString();
    }
}
