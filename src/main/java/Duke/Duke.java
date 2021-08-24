package Duke;

import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.Todo;

import java.util.Scanner;

/**
 * This class represents a Duke instance.
 */
public class Duke {
    private final TaskList list = new TaskList();
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LINE = "____________________________________________________________\n";
    private boolean isStopped = false;

    /**
     * Prints welcome message, then accepts user input until exit command is entered.
     */
    public void start() {
        formatAndPrint(WELCOME_MESSAGE);
        Scanner sc = new Scanner(System.in);
        while (!this.isStopped) {
            String input = sc.nextLine();
            try {
                listen(input);
            } catch (DukeException e) {
                formatAndPrint(e.getMessage());
            }
        }
    }

    public void stop() {
        this.isStopped = true;
    }

    /**
     * Accepts user input and runs the appropriate function.
     *
     * @param input String containing user input.
     * @return Boolean that controls whether to continue accepting user input.
     */
    public void listen(String input) throws DukeException {
        // Get command
        String[] splitCommand = input.split(" ", 2);
        String command = splitCommand[0];
        switch (command) {
        case "bye":
            this.stop();
            formatAndPrint(GOODBYE_MESSAGE);
            break;
        case "list":
            formatAndPrint(list.toString());
            break;
        case "done": {
            // Error handling: number not provided.
            if (splitCommand.length == 1) {
                throw new DukeException("Please provide a number to mark as done.");
            }
            // Retrieve value inputted by user and subtract 1 to get the index in the array.
            int index = Integer.parseInt(splitCommand[1]) - 1;
            // Error handling: negative number or number more than list length.
            if (index < 0 || index >= list.size()) {
                throw new DukeException("Invalid number.");
            }
            Task task = this.list.setDone(index);
            formatAndPrint("Nice! I've marked this task as done:\n" + task);
            break;
        }
        case "delete": {
            // Error handling: number not provided.
            if (splitCommand.length == 1) {
                throw new DukeException("Please provide a number to delete.");
            }
            // Retrieve value inputted by user and subtract 1 to get the index in the array.
            int index = Integer.parseInt(splitCommand[1]) - 1;
            // Error handling: negative number or number more than list length.
            if (index < 0 || index >= list.size()) {
                throw new DukeException("Invalid number.");
            }
            Task task = list.remove(index);
            formatAndPrint(String.format("Noted. I've removed this task:\n%s\nNow you have %d %s in your list.",
                    task,
                    list.size(),
                    list.size() == 1 ? "task" : "tasks"));
            break;
        }
        case "todo":
            // Error handling: no task name.
            if (splitCommand.length == 1) {
                throw new DukeException("Description of a todo cannot be empty.");
            }
            addToList(new Todo(splitCommand[1]));
            break;
        case "deadline": {
            // Error handling: no task name.
            if (splitCommand.length == 1) {
                throw new DukeException("Description of a deadline cannot be empty.");
            }
            // Split deadline message from due date
            String[] splitMessage = splitCommand[1].split(" /by ", 2);
            // Error handling: no due date.
            if (splitMessage.length == 1) {
                throw new DukeException("Due date cannot be empty.");
            }
            addToList(new Deadline(splitMessage[0], splitMessage[1]));
            break;
        }
        case "event": {
            // Error handling: no task name.
            if (splitCommand.length == 1) {
                throw new DukeException("Description of a event cannot be empty.");
            }
            // Split event message from due date
            String[] splitMessage = splitCommand[1].split(" /at ", 2);
            // Error handling: no time provided.
            if (splitMessage.length == 1) {
                throw new DukeException("Time cannot be empty.");
            }
            addToList(new Event(splitMessage[0], splitMessage[1]));
            break;
        }
        default:
            throw new DukeException("Sorry, I don't understand this command.");
        }
    }

    /**
     * Adds task to the list of tasks.
     *
     * @param task Task that the user inputs.
     */
    public void addToList(Task task) {
        this.list.add(task);
        formatAndPrint(String.format("Got it. I've added this task:\n%s\nNow you have %d %s in your list.",
                task,
                list.size(),
                list.size() == 1 ? "task" : "tasks"));
    }

    /**
     * Helper function to format output between 2 lines.
     *
     * @param s String to be outputted.
     */
    private static void formatAndPrint(String s) {
        System.out.printf("%s%s\n%s", LINE, s, LINE);
    }
}
