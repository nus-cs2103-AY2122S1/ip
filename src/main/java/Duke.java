import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // list used to store text entered by user
    private ArrayList<Task> items = new ArrayList<>();

    // Lines used to indicate a block of message
    private final String HORIZONTAL_LINE_HEAD = "\t____________________________________________________________";
    private final String HORIZONTAL_LINE_TAIL = String.format("\n%s\n", HORIZONTAL_LINE_HEAD);

    // Default messages sent by the chat bot
    private final String WELCOME_MSG = "Hello! I am Matthew!\n\t What can I do for you?";
    private final String EXIT_MSG = "Bye. Don't have a good day... Have a great day!!!";
    private final String DISPLAY_LIST_MSG = "\t Here are the tasks in your list:";
    private final String TASK_DONE_MSG = "Nice! I've marked this task as done: \n\t  ";
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
        Duke chatBot = new Duke();
        chatBot.start();
    }

    /**
     * Starts the chat bot.
     * Chat bot starts receiving commands from user and echo back the command until terminated.
     */
    public void start() {
        greet();

        // scanner to take in user's input(s)
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        while(!input.equalsIgnoreCase(EXIT_TAG)) {
            try {
                checkTag(input);
            } catch (DukeException e) {
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
     * Greets the users when chat bot is started.
     */
    private void greet() {
        printFormattedMsg(WELCOME_MSG);
    }

    /**
     * Greets the users when chat bot is terminated.
     */
    private void exit() {
        printFormattedMsg(EXIT_MSG);
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
                displayTasks();
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
            printFormattedMsg(e.getMessage());
        }
    }

    /**
     * Add an event to the list.
     *
     * @param msg Input from user
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void addEvent(String msg) throws IllegalFormatException{
        Task newTask = new Event(getTaskDesc(msg), getTaskDates(msg));
        addTask(newTask);
    }

    /**
     * Add a deadline to the list.
     *
     * @param msg Input from user
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void addDeadline(String msg) throws IllegalFormatException{
        Task newTask = new Deadline(getTaskDesc(msg), getTaskDates(msg));
        addTask(newTask);
    }

    /**
     * Add a todo to the list.
     *
     * @param msg Input from user.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void addTodo(String msg) throws IllegalFormatException{
        Task newTask = new Todo(getTodoDesc(msg));
        addTask(newTask);
    }

    /**
     * Display the list of task that has been added.
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
     * Mark the task as completed once it is done.
     *
     * @param msg Input from user.
     * @throws NoSuchTaskException Invalid id used.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private void taskDone(String msg) throws NoSuchTaskException, IllegalFormatException{
        int index = getTaskId(msg);
        Task item = this.items.get(index);
        item.taskCompleted();

        String content = String.format(TASK_DONE_MSG + item);
        printFormattedMsg(content);
    }

    private void deleteTask(String msg) throws NoSuchTaskException, IllegalFormatException {
        int index = getTaskId(msg);
        Task task = this.items.get(index);
        this.items.remove(index);

        printFormattedMsg(TASK_DELETED_MSG + task + "\n\t Now you have " + getTaskCount() + " tasks in the list.");
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
        int position = msg.indexOf(" ");

        if (position >= msg.length() || position < 0) {
            throw new IllegalFormatException();
        } else {
            try {
                int index = Integer.parseInt(msg.substring(position + 1)) - 1;

                if (index >= items.size()) {
                    throw new NoSuchTaskException();
                }

                if (index > msg.length() || index < 0) {
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
     * @param msg Input from user
     * @return A String representing the description of the task.
     * @throws StringIndexOutOfBoundsException Wrong Format used by the user.
     */
    private String getTodoDesc(String msg) throws IllegalFormatException{
        int position = msg.indexOf(" ");

        if (position >= msg.length() || position < 0) {
            throw new IllegalFormatException();
        }

        return msg.substring(position).trim();
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
     * Returns the date/time for the deadline/event respectively.
     *
     * @param msg Input from user.
     * @return A String representing the date (deadline type) / time (event type).
     * @throws IllegalFormatException Wrong Format used by the user.
     */
    private String getTaskDates(String msg) throws IllegalFormatException{
        int position = msg.indexOf("/") + 2;

        if (position < 2 || position >= msg.length()) {
            throw new IllegalFormatException();
        }

        return msg.substring(position + 1).trim();
    }

    /**
     * Add a new task to the list.
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
     * Formats the message; puts the message in a block.
     * Horizontal lines - message - Horizontal lines.
     *
     * @param msg The message to be printed by the chat bot.
     */
    private void printFormattedMsg(String msg) {
        String formattedMsg = String.format("%s\n\t %s%s", HORIZONTAL_LINE_HEAD, msg, HORIZONTAL_LINE_TAIL);
        System.out.println(formattedMsg);
    }
}
