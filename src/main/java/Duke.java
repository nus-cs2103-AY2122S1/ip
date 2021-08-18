import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    // Array for storing user inputs
    private static final Task[] store = new Task[100];
    private static int count = 0;

    // Regex pattern for finding done commands
    private static final Pattern DONE_PATTERN = Pattern.compile("^done (\\d*)$");

    // Regex pattern for finding done commands
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo (.*)$");

    // Regex pattern for finding done commands
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline (.*) /by (.*)$");

    // Regex pattern for finding done commands
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event (.*) /at (.*)$");

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner((System.in));

        say("Hello, I'm Duke.", "Make me do something.");

        while (true) {
            String userInput = scanner.nextLine().strip();

            if (userInput.equals("bye")) {
                // Check if user is attempting to exit.
                say("Bye bye, see you next time.");
                break;
            } else if (userInput.equals("list")) {
                // Check if user is requesting to print list.
                list();
            } else if (DONE_PATTERN.matcher(userInput).matches()) {
                // Check if user is attempting to mark a task as done.
                Matcher matcher =  DONE_PATTERN.matcher(userInput);
                matcher.find();
                String taskPositionString = matcher.group(1);

                int taskPosition = Integer.parseInt(taskPositionString);
                markAsDone(taskPosition);
            } else if (TODO_PATTERN.matcher(userInput).matches()) {
                // User is attempting to add a to-do
                addToDo(userInput);
            } else if (DEADLINE_PATTERN.matcher(userInput).matches()) {
                // User is attempting to add a deadline
                addDeadline(userInput);
            } else if (EVENT_PATTERN.matcher(userInput).matches()) {
                // User is attempting to add an event
                addEvent(userInput);
            }
        }
    }

    /**
     * Makes duke speak.
     *
     * Prints a list of lines with indentation.
     * @param lines list of lines for Duke
     */
    static void say(String... lines) {
        System.out.println("    ____________________________________________________________");
        for (String line : lines) {
            System.out.println("    " + line);
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the list of inputs that Duke has stored.
     */
    static void list() {

        if (count == 0) {
            // Inform user if nothing has been stored.
            say("The list is empty!");
            return;
        }

        String[] listItems = new String[count];

        for (int i = 0; i < count; ++i) {
            listItems[i] = String.format("%d. %s", i + 1, store[i]);
        }

        say(listItems);
    }

    /**
     * Adds a new task to the store and informs the user.
     * @param taskDescription description of the new task
     */
    private static void addToStore(String taskDescription) {
        Task task = new Task(taskDescription, false);

        store[count] = task;
        count++;

        say("Added:", String.format("%d. %s", count, task));
    }

    /**
     * Marks a task at taskPosition as done.
     * @param taskPosition Position of the task to be marked
     */
    private static void markAsDone(int taskPosition) {
        if (taskPosition < 0 || taskPosition > count) {
            say(String.format("There is no task number %d!", taskPosition));
            return;
        }

        Task task = store[taskPosition - 1];

        // Mark task as completed
        task.complete();

        say("I have marked the task as done!", String.format("%d. %s", taskPosition, task));
    }

    /**
     * Adds a to-do which contains a description and no date/time.
     * @param userInput User input to be split by regex pattern matching
     */
    private static void addToDo(String userInput) {
        Matcher matcher = TODO_PATTERN.matcher(userInput);
        if (!matcher.find()) {
            return;
        }
        String description = matcher.group(1);

        ToDo todo = new ToDo(description, false);

        // Add to store
        store[count] = todo;
        count++;

        // Inform user
        say("I have added a ToDo!", String.format("%d. %s", count, todo));
    }

    /**
     * Adds a to-do which contains a description and an end date/time.
     * @param userInput User input to be split by regex pattern matching
     */
    private static void addDeadline(String userInput) {
        Matcher matcher = DEADLINE_PATTERN.matcher(userInput);
        if (!matcher.find()) {
            return;
        }
        String description = matcher.group(1);
        String endDateTime = matcher.group(2);

        Deadline deadline = new Deadline(description, false, endDateTime);

        // Add to store
        store[count] = deadline;
        count++;

        // Inform user
        say("I have added a new deadline!", String.format("%d. %s", count, deadline));
    }

    /**
     * Adds an event which contains a description and a start and end date/time.
     * @param userInput User input to be split by regex pattern matching
     */
    private static void addEvent(String userInput) {
        Matcher matcher = EVENT_PATTERN.matcher(userInput);
        if (!matcher.find()) {
            return;
        }
        String description = matcher.group(1);
        String eventDateTime = matcher.group(2);

        Event event = new Event(description, false, eventDateTime);

        // Add to store
        store[count] =  event;
        count++;

        // Inform user
        say("I have added a new event!", String.format("%d. %s", count, event));
    }
}
