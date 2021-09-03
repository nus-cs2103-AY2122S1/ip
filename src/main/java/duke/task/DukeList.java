package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.DukeException;


/**
 * Encapsulation of the list in Duke.
 */
public class DukeList {

    /** The list in which Duke stores Tasks. */
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a DukeList.
     */
    public DukeList() {}

    /**
     * Displays the addition of a task.
     *
     * @param task The task to be displayed.
     * @return The message associated to the task being displayed.
     */
    private String displayTask(Task task) {
        String response = "Got it. I've added this task:\n";
        String taskCount = "\nNow you have " + tasks.size() + " tasks in the list.";
        return response + task.toString() + taskCount;
    }

    /**
     * Adds a task to the list.
     *
     * @param text Description of the task to be added.
     */
    public void add(String text) {
        tasks.add(new Task(text));
        System.out.println("added: " + text);
    }

    /**
     * Loads task data obtained from the save file into the list.
     *
     * @param type Type of task to be loaded.
     * @param state Done state of the task to be loaded.
     * @param body Description of the task to be loaded.
     * @throws DukeException When unknown task type is loaded.
     */
    public void loadData(String type, String state, String body) throws DukeException {
        Task task;
        String[] sections;

        switch (type) {
        case "todo":
            task = new ToDo(body);
            break;
        case "deadline":
            sections = body.split(" /by ", 2);
            task = new Deadline(sections[0], sections[1]);
            break;
        case "event":
            sections = body.split(" /at ", 2);
            task = new Event(sections[0], sections[1]);
            break;
        default:
            throw new DukeException("☹ OOPS!!! Unknown task type in saved data");
        }

        if (state.equals("1")) {
            task.setDone();
        }

        this.tasks.add(task);
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param text Body of the duke.task to be added.
     * @return The message associated to the task being displayed.
     * @throws DukeException If there is no text.
     */
    public String addToDo(String text) throws DukeException {
        String message = text.trim();

        if (message.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        ToDo input = new ToDo(message);

        tasks.add(input);

        return displayTask(input);
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param text Body of the task to be added.
     * @return The message associated to the task being displayed.
     */
    public String addDeadline(String text) throws DukeException {
        String[] strings = text.split(" /by ", 2);

        String limit = strings[1];
        try {
            Deadline input = new Deadline(strings[0].trim(), limit);

            tasks.add(input);

            return displayTask(input);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The deadline follows the format yyyy-MM-dd.");
        }
    }

    /**
     * Adds an Event task to the list.
     *
     * @param text Body of the task to be added.
     * @return The message associated to the task being displayed.
     */
    public String addEvent(String text) {
        String[] strings = text.split(" /at ", 2);

        String limit = strings.length == 1 ? "" : strings[1];

        Event input = new Event(strings[0].trim(), limit);

        tasks.add(input);

        return displayTask(input);
    }

    /**
     * Lists out the current tasks in the list.
     *
     * @return The list of tasks in the list.
     */
    public String list() {
        StringBuilder str = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            str.append(i + 1)
                    .append(".")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }

        return str.toString();
    }

    /**
     * Marks a task in the list as done.
     *
     * @param item Index of the task marked as done.
     * @return The message associated to the task being done.
     */
    public String done(int item) {
        Task task = tasks.get(item - 1);
        task.setDone();

        String response = "Nice! I've marked this task as done:\n";
        return response + task.toString();
    }

    /**
     * Deletes a task from the list.
     *
     * @param item Index of the task to be deleted.
     * @return The message associated to the task being deleted.
     */
    public String delete(int item) {
        Task task = tasks.get(item - 1);
        tasks.remove(item - 1);

        String response = "Noted. I've removed this task:\n";
        String taskCount = "\nNow you have " + tasks.size() + " tasks in the list.";
        return response + task.toString() + taskCount;
    }

    /**
     * Finds all tasks in list with the input keyword.
     *
     * @param input User input keyword.
     * @return The response to the number of tasks found.
     */
    public String find(String input) {
        String trimmedInput = input.trim();
        StringBuilder str = new StringBuilder();
        String msg;

        int count = 0;

        boolean doesContain;

        for (int i = 0; i < tasks.size(); i++) {
            msg = tasks.get(i).toString();
            doesContain = msg.contains(trimmedInput);

            if (doesContain) {
                str.append(i + 1).append(".").append(msg).append("\n");
                count++;
            }
        }

        if (count == 0) {
            return "Sorry no tasks found that match your input.";
        } else {
            return str + "\nAre these the tasks you're looking for?";
        }
    }

    /**
     * Returns the string form of the DukeList object.
     *
     * @return The string form of the DukeList.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            str.append(tasks.get(i).saveData()).append("\n");
        }

        return str.toString();
    }


}
