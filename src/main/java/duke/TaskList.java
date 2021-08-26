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
     */
    public void add(Task task) {
        list.add(task);
        System.out.println("added: " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    /**
     * Adds the task into the list without print statements.
     *
     * @param task task provided to add to the list.
     */
    public void read(Task task) {
        list.add(task);
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
            throw new DukeException("☹ OOPS!!! No such task found!");
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
     */
    public void display() {
        for (int i = 0; i < list.size(); i++) {
            String cur = list.get(i).toString();
            int label = i + 1;
            System.out.println(label + ". " + cur);
        }
    }

    /**
     * Deletes a task entry from the list.
     *
     * @param pos index of the task the user wants to delete.
     * @throws DukeException when no task is found at the index.
     */
    public void delete(int pos) throws DukeException {
        if (pos - 1 < 0 || pos > list.size()) {
            throw new DukeException("☹ OOPS!!! No such task found!");
        }
        Task temp = list.get(pos - 1);
        list.remove(pos - 1);
        System.out.println("Noted, I have removed this task:");
        System.out.println(temp.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    public void find(String string) throws DukeException {
        if (string.isEmpty()) {
            throw new DukeException("☹ OOPS!!! No input was detected!");
        }
        int counter = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : list) {
            String cur = task.toString();
            if (cur.contains(string)) {
                int label = counter + 1;
                System.out.println(label + ". " + cur);
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("☹ OOPS!!! No items found!");
        }
    }

}
