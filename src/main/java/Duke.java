import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    // Array for storing user inputs
    private static final Task[] store = new Task[100];
    private static int count = 0;

    // Regex pattern for finding done commands
    private static final Pattern DONE_PATTERN = Pattern.compile("^done (\\d*)$");

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
                continue;
            } else if (DONE_PATTERN.matcher(userInput).matches()) {
                // Check if user is attempting to mark a task as done.
                Matcher matcher =  DONE_PATTERN.matcher(userInput);
                matcher.find();
                String taskPositionString = matcher.group(1);

                int taskPosition = Integer.parseInt(taskPositionString);
                markAsDone(taskPosition);
                continue;
            }

            // Add input to list and inform user.
            addToStore(userInput);
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
}
