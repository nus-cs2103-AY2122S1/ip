import exceptions.DukeException;
import exceptions.IllegalFormatException;
import exceptions.NoSuchTaskException;
import exceptions.UnknownTagException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // list used to store task entered by user
    private ArrayList<Task> items;

    private final boolean DEFAULT_TASK_STATUS = false;

    // space used to store data entered by user
    private Storage storage;

    // Lines used to indicate a block of message
    private final String HORIZONTAL_LINE_HEAD = "\t____________________________________________________________";
    private final String HORIZONTAL_LINE_TAIL = String.format("\n%s\n", HORIZONTAL_LINE_HEAD);

    // Default messages sent by the chat bot
    private final String WELCOME_MSG = "Hello! I am Matthew!\n\t What can I do for you?";
    private final String EXIT_MSG = "Bye. Don't have a good day... Have a great day!!!";
    private final String DISPLAY_LIST_MSG = "\t Here are the tasks in your list:";
    private final String TASK_DONE_MSG = "Well done! You have completed: \n\t  ";
    private final String TASK_ADDED_MSG = "Got it. I've added this task:\n\t   ";
    private final String TASK_DELETED_MSG = "Got it. I've deleted this task:\n\t   ";


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

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath File path of the data file.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
    }

    /**
     * Starts the chat bot.
     * Chat bot starts receiving commands from user and echo back the command until terminated.
     */
    public void start() {
        // attempt to load past data from the given filepath
        try {
            items = storage.loadTask();
        } catch (IOException | NoSuchTaskException e) {
            printFormattedMsg(e.getMessage());
        }

        // prints welcome message
        greet();

        // scanner to take in user's input(s)
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();


        while(!input.equalsIgnoreCase(EXIT_TAG)) {
            try {
                // check the input tag
                checkTag(input);

                // updates the data storage
                storage.saveTask(items);
            } catch (DukeException | IOException e) {
                printFormattedMsg(e.getMessage());
            } finally {
                input = scanner.nextLine().trim();
            }
        }

        // close the scanner as the bot is terminated.
        scanner.close();
        exit();
    }

    /**
     * Prints the welcome message when the chat bot is started.
     */
    private void greet() {
        printFormattedMsg(WELCOME_MSG);
    }

    /**
     * Prints the goodbye message when the chat bot is terminated.
     */
    private void exit() {
        printFormattedMsg(EXIT_MSG);
    }

    /**
     * Checks if there is a tag in the user input that matches one of the tags recognised
     * by the chat bot. If it matches, carry out to corresponding activity.
     * Otherwise, throws an UnknownTagException that indicates that the format used is wrong.
     *
     * @param input User's input.
     * @throws UnknownTagException Tag used is undefined.
     */
    private void checkTag(String input) throws UnknownTagException {
        String msgToCheck = input.toLowerCase();
        try {
            if (msgToCheck.equals(LIST_TAG)) {
                displayTasks();
            } else if (msgToCheck.contains(DONE_TAG)) {
                taskDone(input);
            } else if (msgToCheck.contains(TODO_TAG)) {
                addTodo(input);
            } else if (msgToCheck.contains(DEADLINE_TAG)) {
                addDeadline(input);
            } else if (msgToCheck.contains(EVENT_TAG)) {
                addEvent(input);
            } else if (input.contains(DELETE_TAG)) {
                deleteTask(input);
            } else {
                throw new UnknownTagException();
            }
        } catch (DukeException e) {
            printFormattedMsg(e.getMessage());
        }
    }

    /**
     * Adds an event to the list.
     *
     * @param input User's input
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void addEvent(String input) throws IllegalFormatException{
        Task newTask = new Event(getTaskDesc(input), getTaskDates(input), DEFAULT_TASK_STATUS);
        addTask(newTask);
    }

    /**
     * Adds a deadline to the list.
     *
     * @param input User's input
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void addDeadline(String input) throws IllegalFormatException{
        Task newTask = new Deadline(getTaskDesc(input), getTaskDates(input), DEFAULT_TASK_STATUS);
        addTask(newTask);
    }

    /**
     * Adds a todo to the list.
     *
     * @param input User's input.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void addTodo(String input) throws IllegalFormatException{
        Task newTask = new Todo(getTodoDesc(input), DEFAULT_TASK_STATUS);
        addTask(newTask);
    }

    /**
     * Displays the list of task that has been added by the user.
     */
    private void displayTasks() {
        System.out.println(HORIZONTAL_LINE_HEAD);
        System.out.println(DISPLAY_LIST_MSG);

        for (int i = 0; i < this.items.size(); i++) {
            Task item = this.items.get(i);
            String formattedMsg = String.format("\t %s.%s", (i + 1), item);

            System.out.println(formattedMsg);
        }

        System.out.println(HORIZONTAL_LINE_TAIL);
    }

    /**
     * Marks the task as completed once it is done.
     *
     * @param input User's input.
     * @throws NoSuchTaskException Invalid id used, id used is larger/smaller than the number of tasks in the list.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void taskDone(String input) throws NoSuchTaskException, IllegalFormatException{
        int index = getTaskId(input);
        Task item = this.items.get(index);
        item.taskCompleted();

        String content = String.format(TASK_DONE_MSG + item);
        printFormattedMsg(content);
    }

    /**
     * Deletes the task as requested by the user.
     *
     * @param input User's input
     * @throws NoSuchTaskException Invalid id used, id used is larger/smaller than the number of tasks in the list.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void deleteTask(String input) throws NoSuchTaskException, IllegalFormatException {
        int index = getTaskId(input);
        Task task = this.items.get(index);
        this.items.remove(index);

        printFormattedMsg(TASK_DELETED_MSG + task + "\n\t Now you have " + getTaskCount() + " tasks in the list.");
    }

    /**
     * Returns the id of the task that has been completed.
     *
     * @param input User's input
     * @return An integer indicating the id of the task that has been completed.
     * @throws NoSuchTaskException Invalid id used, id used is larger/smaller than the number of tasks in the list.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private int getTaskId(String input) throws NoSuchTaskException, IllegalFormatException {
        int position = input.indexOf(" ");

        if (position >= input.length() || position < 0) {
            throw new IllegalFormatException();
        } else {
            try {
                int index = Integer.parseInt(input.substring(position + 1)) - 1;

                if (index >= items.size()) {
                    throw new NoSuchTaskException();
                }

                if (index > input.length() || index < 0) {
                    throw new IllegalFormatException();
                }

                return index;
            } catch (NumberFormatException e) {
                // input after a whitespace is not a number.
                throw new IllegalFormatException();
            }
        }
    }

    /**
     * Returns the description for a todo type task.
     *
     * @param input User's input
     * @return A String representing the description of the task.
     * @throws StringIndexOutOfBoundsException Wrong Format used by the user.
     */
    private String getTodoDesc(String input) throws IllegalFormatException{
        int position = input.indexOf(" ");

        if (position >= input.length() || position < 0) {
            throw new IllegalFormatException();
        }

        return input.substring(position).trim();
    }

    /**
     * Returns the description for a deadline or event type task.
     *
     * @param input User's input.
     * @return A String representing the description of the task.
     * @throws IllegalFormatException Wrong Format used by the user.
     */
    private String getTaskDesc(String input) throws IllegalFormatException{
        int startPosition = input.indexOf(" ");
        int endPosition = input.indexOf("/");

        if (startPosition < 0 || startPosition >= input.length() || endPosition < 0 || endPosition >= input.length()) {
            throw new IllegalFormatException();
        }

        return input.substring(startPosition, endPosition).trim();
    }

    /**
     * Returns the date/time for the deadline/event respectively.
     *
     * @param input Input from user.
     * @return A String representing the date (deadline type) / time (event type).
     * @throws IllegalFormatException Wrong Format used by the user.
     */
    private String getTaskDates(String input) throws IllegalFormatException{
        int position = input.indexOf("/") + 2;

        if (position < 2 || position >= input.length()) {
            throw new IllegalFormatException();
        }

        return input.substring(position + 1).trim();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The new task to be added
     */
    private void addTask(Task task) {
        this.items.add(task);
        printFormattedMsg(TASK_ADDED_MSG + task + "\n\t Now you have " + getTaskCount() + " tasks in the list.");
    }

    /**
     * Returns the current number of items in the list.
     *
     * @return An int representing the current number of items in th list.
     */
    private int getTaskCount() {
        return this.items.size();
    }

    /**
     * Formats the message; puts the message in a block wrap in between two horizontal lines.
     * Format: Horizontal lines - message - Horizontal lines.
     *
     * @param msg The message to be printed by the chat bot.
     */
    private void printFormattedMsg(String msg) {
        String formattedMsg = String.format("%s\n\t %s%s", HORIZONTAL_LINE_HEAD, msg, HORIZONTAL_LINE_TAIL);
        System.out.println(formattedMsg);
    }
}
