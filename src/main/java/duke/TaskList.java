package duke;

import java.util.ArrayList;

/**
 * TaskList is the wrapper around ArrayList to store the tasks created.
 *
 * @author meerian
 */
public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds the task into the list.
     *
     * @param task task provided to add to the list.
     * @return the resulting string to display.
     */
    public String add(Task task) {
        String result;
        list.add(task);
        result = "added: " + task + "\n";
        return result + "Now you have " + list.size() + " tasks in the list\n" + "uwu";
    }

    /**
     * Fetches the task specified by the user.
     *
     * @param pos index of the task the user wants.
     * @return the task in the corresponding index.
     * @throws DukeException when no task is found at the index.
     */
    public Task get(int pos) throws DukeException {
        if (pos < 0 || pos > list.size()) {
            throw new DukeException("OOPS!!! No such task found!");
        }
        return list.get(pos);
    }

    /**
     * Returns the size of the current task list.
     *
     * @return the size of the current task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Prints out a visual representation of the current task list
     * in a legible manner.
     *
     * @return the resulting string to display.
     */
    public String display() {
        StringBuilder result = new StringBuilder("Your List:\n");
        for (int i = 0; i < list.size(); i++) {
            String cur = list.get(i).toString();
            int label = i + 1;
            result.append(label).append(". ").append(cur).append("\n");
        }
        return result.toString();
    }

    /**
     * Deletes a task entry from the list.
     *
     * @param pos index of the task the user wants to delete.
     * @return the resulting string to display.
     * @throws DukeException when no task is found at the index.
     */
    public String delete(int pos) throws DukeException {
        String result;
        if (pos - 1 < 0 || pos > list.size()) {
            throw new DukeException("OOPS!!! No such task found!");
        }
        Task temp = list.get(pos - 1);
        list.remove(pos - 1);
        result = "Noted, I have removed this task:\n" + temp.toString() + "\n";
        return result + "Now you have " + list.size() + " tasks in the list\nuwu";
    }

    /**
     * Finds entries in the list with the provided keyword
     *
     * @param string keyword to search the list with.
     * @return the resulting string to display.
     * @throws DukeException when no input is detected.
     */
    public String find(String string) throws DukeException {
        if (string.isEmpty()) {
            throw new DukeException("OOPS!!! No input was detected!");
        }
        int counter = 0;
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : list) {
            String cur = task.toString();
            if (cur.contains(string)) {
                int label = counter + 1;
                result.append(label).append(". ").append(cur).append("\n");
                counter++;
            }
        }
        if (counter == 0) {
            return "OOPS!!! No items found!";
        } else {
            return result.toString();
        }
    }

}
