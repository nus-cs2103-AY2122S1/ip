import java.util.ArrayList;

/**
 * Encapsulation of the list in Duke.
 */
public class DukeList {
    /** The list in which Duke stores Tasks. */
    private ArrayList<Task> list = new ArrayList<>();
    private int count = 0;

    /**
     * Constructor of a DukeList.
     */
    public DukeList() {}

    /**
     * Displays the addition of a task.
     *
     * @param task The task to be displayed.
     */
    private void displayTask(Task task) {
        String response = "Got it. I've added this task:\n";
        String taskCount = "\nNow you have " + list.size() + " tasks in the list.";
        System.out.println(response + task.toString() + taskCount);
    }

    /**
     * Adds a task to the list.
     *
     * @param text Description of the task to be added.
     */
    public void add(String text) {
        list.add(new Task(text));
        count += 1;
        System.out.println("added: " + text);
    }

    /**
     * Adds a ToDos task to the list.
     *
     * @param text Body of the task to be added.
     * @throws DukeException If there is no text.
     */
    public void addToDo(String text) throws DukeException {
        String message = text.trim();

        if (message.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        ToDos input = new ToDos(message);
        list.add(input);
        count += 1;

        displayTask(input);
    }

    /**
     * Adds a Deadlines task to the list.
     *
     * @param text Body of the task to be added.
     */
    public void addDeadlines(String text) {
        String[] strings = text.split(" /by ", 2);

        String limit = strings.length == 1 ? "" : strings[1];

        Deadlines input = new Deadlines(strings[0].trim(), limit);
        list.add(input);
        count += 1;

        displayTask(input);
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
        list.add(input);
        count += 1;

        displayTask(input);
    }

    /**
     * Lists out the current tasks in the list.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Marks a task in the list as done.
     *
     * @param item Index of the task marked as done.
     */
    public void done(int item) {
        Task task = list.get(item - 1);
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
        Task task = list.get(item - 1);
        list.remove(item - 1);

        String response = "Noted. I've removed this task:\n";
        String taskCount = "\nNow you have " + list.size() + " tasks in the list.";
        System.out.println(response + task.toString() + taskCount);
    }


}
