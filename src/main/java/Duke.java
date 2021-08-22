import exceptions.DukeException;
import exceptions.IllegalFormatException;
import exceptions.NoSuchTaskException;
import exceptions.UnknownTagException;
import tasks.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // list used to store text entered by user
    private TaskList items;

    private Storage storage;

    private final boolean DEFAULT_STATUS = false;

    // Command Tags for the chat bot
    private final String EXIT_TAG = "bye";
    private final String LIST_TAG = "list";
    private final String DONE_TAG = "done";
    private final String TODO_TAG = "todo";
    private final String DEADLINE_TAG = "deadline";
    private final String EVENT_TAG = "event";
    private final String DELETE_TAG = "delete";



    public static void main(String[] args) {
        Duke chatBot = new Duke("data/duke.txt");
        chatBot.start();
    }

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
    }

    /**
     * Starts the chat bot.
     * Chat bot starts receiving commands from user and echo back the command until terminated.
     */
    public void start() {
        try {
            items = storage.loadTask();
        } catch (IOException | NoSuchTaskException e) {
            Ui.notifyError(e.getMessage());
        }

        Ui.greetUser();

        // scanner to take in user's input(s)
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        while(!input.equalsIgnoreCase(EXIT_TAG)) {
            try {
                checkTag(input);
                storage.saveTask(items);
            } catch (DukeException | IOException e) {
                Ui.notifyError(e.getMessage());
            } finally {
                input = scanner.nextLine().trim();
            }
        }

        // close the scanner as the bot is terminated.
        scanner.close();
        Ui.exit();
    }

    /**
     * Check if the inputted string match the 'list' tag.
     * Prints the list of items if inputted msg is 'list.
     * Otherwise, adds the item to the list.
     *
     * @param msg User's input.
     * @throws UnknownTagException Tag used is undefined.
     */
    private void checkTag(String msg) throws UnknownTagException {
        String msgToCheck = msg.toLowerCase();
        try {
            if (msgToCheck.equals(LIST_TAG)) {
                displayTasks(this.items);
            } else if (msgToCheck.contains(DONE_TAG)) {
                taskDone(msg);
            } else if (msgToCheck.contains(TODO_TAG)) {
                addTodo(msg);
            } else if (msgToCheck.contains(DEADLINE_TAG)) {
                addDeadline(msg);
            } else if (msgToCheck.contains(EVENT_TAG)) {
                addEvent(msg);
            } else if (msg.contains(DELETE_TAG)) {
                deleteTask(msg);
            } else {
                throw new UnknownTagException();
            }
        } catch (DukeException e) {
            Ui.notifyError(e.getMessage());
        }
    }

    /**
     * Add an event to the list.
     *
     * @param msg Input from user
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void addEvent(String msg) throws IllegalFormatException{
        Task newTask = new Event(getTaskDesc(msg), getEventDates(msg), DEFAULT_STATUS);
        items.addTask(newTask);
    }

    /**
     * Add a deadline to the list.
     *
     * @param msg Input from user
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void addDeadline(String msg) throws IllegalFormatException{
        Task newTask = new Deadline(getTaskDesc(msg), getDeadlineDates(msg), DEFAULT_STATUS);
        items.addTask(newTask);
    }

    /**
     * Add a todo to the list.
     *
     * @param msg Input from user.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void addTodo(String msg) throws IllegalFormatException{
        Task newTask = new Todo(getTodoDesc(msg), DEFAULT_STATUS);
        items.addTask(newTask);
    }

    /**
     * Display the list of task that has been added.
     */
    private void displayTasks(TaskList taskList) {
        Ui.displayList(taskList);
    }

    /**
     * Mark the task as completed once it is done.
     *
     * @param msg Input from user.
     * @throws NoSuchTaskException Invalid id used.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void taskDone(String msg) throws NoSuchTaskException, IllegalFormatException{
        int index = getTaskId(msg);
        Task item = this.items.getTask(index);
        item.taskCompleted();

        Ui.taskDoneMessage(item);
    }

    private void deleteTask(String msg) throws NoSuchTaskException, IllegalFormatException {
        int index = getTaskId(msg);
        Task task = this.items.getTask(index);
        this.items.deleteTask(index);

        Ui.taskDeletedMessage(task, items.getTaskCount());
    }

    /**
     * Returns the id of the task that has been completed.
     *
     * @param msg Input of user
     * @return An integer indicating the id of the task that has been completed.
     * @throws NoSuchTaskException Invalid id enter.
     * @throws IllegalFormatException Invalid id enter.
     */
    private int getTaskId(String msg) throws NoSuchTaskException, IllegalFormatException {
        String[] details = msg.split(" ");

        if (details.length != 2) {
            throw new IllegalFormatException();
        }

        try {
            int index = Integer.valueOf(details[1]) - 1;

            if (index >= items.getTaskCount()) {
                throw new NoSuchTaskException();
            }

            return index;
        } catch (NumberFormatException e) {
            throw new IllegalFormatException();
        }
    }

    /**
     * Returns the description for a todo type task.
     *
     * @param msg Input from user
     * @return A String representing the description of the task.
     * @throws StringIndexOutOfBoundsException Wrong Format used by the user.
     */
    private String getTodoDesc(String msg) throws IllegalFormatException {
        String[] details = msg.split("todo ");

        if (details.length != 2) {
            throw new IllegalFormatException();
        }

        return details[1].trim();
    }

    /**
     * Returns the description for a deadline or event type task.
     *
     * @param msg Input from user
     * @return A String representing the description of the task.
     * @throws IllegalFormatException Wrong Format used by the user.
     */
    private String getTaskDesc(String msg) throws IllegalFormatException{
        int startPosition = msg.indexOf(" ");
        int endPosition = msg.indexOf("/");

        if (startPosition < 0 || startPosition >= msg.length() || endPosition < 0 || endPosition >= msg.length()) {
            throw new IllegalFormatException();
        }

        return msg.substring(startPosition, endPosition).trim();
    }

    /**
     * Returns the date for the deadline.
     *
     * @param msg Input from user.
     * @return A LocalDate representing the date.
     * @throws IllegalFormatException Wrong Format used by the user.
     */
    private LocalDate getDeadlineDates(String msg) throws IllegalFormatException{
        String[] details = msg.split("/by ");

        if (details.length != 2) {
            throw new IllegalFormatException();
        }

        return LocalDate.parse(details[1]);
    }

    /**
     * Returns the date for the event.
     *
     * @param msg Input from user.
     * @return A LocalDate representing the date..
     * @throws IllegalFormatException Wrong Format used by the user.
     */
    private LocalDate getEventDates(String msg) throws IllegalFormatException{
        String[] details = msg.split("/at ");

        if (details.length != 2) {
            throw new IllegalFormatException();
        }

        return LocalDate.parse(details[1]);
    }




}
