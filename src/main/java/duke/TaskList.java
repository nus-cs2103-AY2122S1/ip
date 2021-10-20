package duke;

import static duke.Ui.dukePrint;

import java.util.ArrayList;
import java.util.stream.IntStream;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.RecurringTask;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A list of Tasks recorded by Duke.
 */
public class TaskList {

    private final ArrayList<Task> LIST;
    private final Storage STORAGE;

    /**
     * Constructor for TaskList.
     *
     * @param list    List of Tasks
     * @param storage Storage object of Duke
     */
    public TaskList(ArrayList<Task> list, Storage storage) {
        this.LIST = list;
        this.STORAGE = storage;
    }

    /**
     * Constructor for TaskList.
     *
     * @param storage Storage object of Duke
     */
    public TaskList(Storage storage) {
        this.LIST = new ArrayList<>();
        this.STORAGE = storage;
    }


    /**
     * Deletes task from list based on user input.
     *
     * @param str User input of what task to delete
     * @throws DukeException Exception based on invalid user input
     */
    public String deleteTask(String str) throws DukeException {
        try {
            int i = Integer.parseInt(str);

            if (i > 0 && i <= LIST.size()) {
                Task t = LIST.remove(i - 1);
                STORAGE.saveFile(LIST);
                return dukePrint("Got it. I've removed this task:\n" + t + "\n" + "Now you have "
                        + LIST.size() + " task" + (LIST.size() < 2 ? " " : "s ") + "in the list.");
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
    public String markDone(String str) throws DukeException {
        try {
            int i = Integer.parseInt(str);

            if (i > 0 && i <= LIST.size()) {
                Task t = LIST.get(i - 1);
                t.markDone();
                STORAGE.saveFile(LIST);
                return dukePrint("Nice! I've marked this task as done:\n" + t);
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
    public String addEvent(String str) throws DukeException {
        if (str.contains("/at ")) {
            String[] arr = str.split("/at ", 2);
            Task t = new Event(arr[0], arr[1]);
            return addTask(t);
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
    public String addDeadline(String str) throws DukeException {
        if (str.contains("/by ")) {
            String[] arr = str.split("/by ", 2);
            Task t = new Deadline(arr[0], arr[1]);
            return addTask(t);
        } else {
            throw new DukeException("Date cannot be empty!");
        }
    }

    /**
     * Add ToDo to list with description from user input.
     *
     * @param str User input of description
     * @throws DukeException Exception based on invalid user input
     */
    public String addToDo(String str) throws DukeException {
        Task t = new ToDo(str);
        return addTask(t);
    }

    private String addTask(Task t) throws DukeException {
        LIST.add(t);
        STORAGE.saveFile(LIST);
        return dukePrint("Got it. I've added this task:\n" + t + "\n" + "Now you have "
                + LIST.size() + " task" + (LIST.size() < 2 ? " " : "s ") + "in the list.");
    }

    /**
     * Add Deadline to list with description from user input.
     *
     * @param str User input of description
     * @throws DukeException Exception based on invalid user input
     */
    public String addRecurringTask(String str) throws DukeException {
        if (!str.contains("/at")) {
            throw new DukeException("Date cannot be empty!");
        }
        String[] arr = str.split("/at ", 2);
        if (!arr[1].contains("/repeat ")) {
            throw new DukeException("Repeat amount cannot be empty!");
        }
        String[] descriptions = str.split("/repeat ", 2);
        Task t = new RecurringTask(arr[0], descriptions[0], descriptions[1]);
        return addTask(t);
    }

    /**
     * Displays full list of task in TaskList.
     */
    public String displayList() {
        if (LIST.size() == 0) {
            return dukePrint("list empty");
        }
        return dukePrint("Here are the tasks in your list:\n"
                + IntStream.range(0, LIST.size()).mapToObj((i) -> (i + 1) + ". " + LIST.get(i).toString())
                .reduce("", (str1, str2) -> str1 + str2 + "\n"));
    }

    /**
     * Prints list of tasks filtered based on user input.
     *
     * @param desc user input to find in task list
     */
    public String findTask(String desc) {
        StringBuilder builder = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < LIST.size(); i++) {
            if (LIST.get(i).toString().contains(desc)) {
                count++;
                builder.append(count + ". " + LIST.get(i).toString() + "\n");
            }
        }
        if (count == 0) {
            return dukePrint("No tasks in list were matched.");
        } else {
            return dukePrint(builder.toString());
        }
    }
}
