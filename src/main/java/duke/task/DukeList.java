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
     */
    private void displayTask(Task task) {
        String response = "Got it. I've added this task:\n";
        String taskCount = "\nNow you have " + tasks.size() + " tasks in the list.";
        System.out.println(response + task.toString() + taskCount);
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
            task = new ToDos(body);
            break;
        case "deadline":
            sections = body.split(" /by ", 2);
            task = new Deadlines(sections[0], sections[1]);
            break;
        case "event":
            sections = body.split(" /at ", 2);
            task = new Events(sections[0], sections[1]);
            break;
        default:
            throw new DukeException("☹ OOPS!!! Unknown task type in saved data");
        }

        if (state.equals("1")) {
            task.done();
        }

        this.tasks.add(task);
    }

    /**
     * Adds a ToDos task to the list.
     *
     * @param text Body of the duke.task to be added.
     * @throws DukeException If there is no text.
     */
    public void addToDo(String text) throws DukeException {
        String message = text.trim();

        if (message.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        ToDos input = new ToDos(message);

        tasks.add(input);

        displayTask(input);
    }

    /**
     * Adds a Deadlines task to the list.
     *
     * @param text Body of the task to be added.
     */
    public void addDeadlines(String text) throws DukeException {
        String[] strings = text.split(" /by ", 2);

        String limit = strings[1];
        try {
            Deadlines input = new Deadlines(strings[0].trim(), limit);

            tasks.add(input);

            displayTask(input);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The deadline follows the format yyyy-MM-dd.");
        }
    }

    /**
     * Adds an Events task to the list.
     *
     * @param text Body of the task to be added.
     */
    public void addEvents(String text) {
        String[] strings = text.split(" /at ", 2);

        String limit = strings.length == 1 ? "" : strings[1];

        Events input = new Events(strings[0].trim(), limit);

        tasks.add(input);


        displayTask(input);
    }

    /**
     * Lists out the current tasks in the list.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Marks a task in the list as done.
     *
     * @param item Index of the task marked as done.
     */
    public void done(int item) {
        Task task = tasks.get(item - 1);
        task.done();

        String response = "Nice! I've marked this task as done:\n";
        System.out.println(response + task.toString());
    }

    /**
     * Deletes a task from the list.
     *
     * @param item Index of the task to be deleted.
     */
    public void delete(int item) {
        Task task = tasks.get(item - 1);
        tasks.remove(item - 1);

        String response = "Noted. I've removed this task:\n";
        String taskCount = "\nNow you have " + tasks.size() + " tasks in the list.";
        System.out.println(response + task.toString() + taskCount);
    }

    /**
     * Finds all tasks in list with the input keyword.
     *
     * @param input User input keyword.
     */
    public void find(String input) {
        String trimmedInput = input.trim();
        String str = "";
        String msg;

        int count = 0;

        boolean doesContain;

        for (int i = 0; i < tasks.size(); i++) {
            msg = tasks.get(i).toString();
            doesContain = msg.contains(trimmedInput);

            if (doesContain) {
                str += (i + 1 + "." + msg + "\n");
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Sorry no tasks found that match your input.");
        } else {
            System.out.println(str + "Are these the tasks you're looking for?");
        }
    }


    /**
     * Returns the string form of the DukeList object.
     *
     * @return The string form of the DukeList.
     */
    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < tasks.size(); i++) {
            str += tasks.get(i).saveData() + "\n";
        }

        return str;
    }


}
