package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static duke.Ui.dukePrint;

/**
 * A list of Tasks recorded by Duke.
 */
public class TaskList {

    private ArrayList<Task> list;
    private Storage storage;

    /**
     * Constructor for TaskList.
     *
     * @param list List of Tasks
     * @param storage Storage object of Duke
     */
    public TaskList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Constructor for TaskList.
     *
     * @param storage Storage object of Duke
     */
    public TaskList(Storage storage) {
        this.list = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Deletes task from list based on user input.
     *
     * @param str User input of what task to delete
     * @throws DukeException Exception based on invalid user input
     */
    public void delete(String str) throws DukeException {
        try {
            int i = Integer.parseInt(str);

            if (i > 0 && i <= list.size()) {
                Task t =list.remove(i-1);
                storage.saveFile(list);
                dukePrint("Got it. I've removed this task:\n" + t + "\n" + "Now you have " +
                        list.size() + " task" + (list.size() < 2 ? " " : "s ") + "in the list.");
            } else {
                throw new DukeException("No such task found in list.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(str + " cannot be converted to a number.");
        }
    }

    /**
     * Mark task as done based on user input.
     *
     * @param str User input of what task to delete
     * @throws DukeException Exception based on invalid user input
     */
    public void done(String str) throws DukeException {
        try {
            int i = Integer.parseInt(str);

            if (i > 0 && i <= list.size()) {
                Task t = list.get(i - 1);
                t.markDone();
                dukePrint("Nice! I've marked this task as done:\n" + t);
                storage.saveFile(list);
            } else {
                throw new DukeException("No such task found in list.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(str + " cannot be converted to a number.");
        }
    }

    /**
     * Add Event to list with description from user input.
     *
     * @param str User input of description
     * @throws DukeException Exception based on invalid user input
     */
    public void addEvent(String str) throws DukeException {
        if (str.contains("/at ")) {
            String[] arr = str.split("/at ", 2);
            Task t = new Event(arr[0], arr[1]);
            addTask(t);
        } else {
            throw new DukeException("Date cannot be empty!");
        }
    }

    /**
     * Add Deadline to list with description from user input.
     *
     * @param str User input of description
     * @throws DukeException Exception based on invalid user input
     */
    public void addDeadline(String str) throws DukeException {
        if (str.contains("/by ")) {
            String[] arr = str.split("/by ", 2);
            Task t = new Deadline(arr[0], arr[1]);
            addTask(t);
        } else {
            throw new DukeException("Date cannot be empty!");
        }
    }

    /**
     * Add ToDo to list with description from user input.
     * @param str User input of description
     * @throws DukeException Exception based on invalid user input
     */
    public void addToDo(String str) throws DukeException {
        Task t = new ToDo(str);
        addTask(t);
    }

    private void addTask(Task t) throws DukeException {
        list.add(t);
        dukePrint("Got it. I've added this task:\n" + t + "\n" + "Now you have " +
                list.size() + " task" + (list.size() < 2 ? " " : "s ") + "in the list.");
        storage.saveFile(list);
    }

    /**
     * Displays full list of task in TaskList.
     */
    public void displayList() {
        if (list.size() == 0) {
            dukePrint("list empty");
            return;
        }
        dukePrint("Here are the tasks in your list:\n" +
                IntStream.range(0, list.size() ).mapToObj((i)-> Integer.toString(i + 1) + ". " + list.get(i).toString()).reduce("",(str1, str2)->str1 + str2+"\n"));
    }
}
