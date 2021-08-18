import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a Duke instance.
 * User inputs are added to the task list, and the "list" command lists all the user's tasks.
 * Users can exit Duke by typing the "bye" command.
 */
public class Duke {
    private final List<Task> list = new ArrayList<>();
    private static final String WELCOME = "Hello, I'm Duke\nWhat can I do for you?";
    private static final String BYE = "Bye. Hope to see you again soon!";

    /**
     * Prints welcome message, then accepts user input until exit command is entered.
     */
    public void start() {
        formatAndPrint(WELCOME);
        Scanner sc = new Scanner(System.in);
        boolean continueListening = true;
        while (continueListening) {
            String input = sc.nextLine();
            try {
                continueListening = listen(input);
            } catch (DukeException e) {
                formatAndPrint(e.getMessage());
            }
        }
    }

    /**
     * Accepts user input and runs the appropriate function.
     *
     * @param input String containing user input.
     * @return Boolean that controls whether to continue accepting user input.
     */
    public boolean listen(String input) throws DukeException {
        // Get command
        String[] splitCommand = input.split(" ", 2);
        String command = splitCommand[0];
        switch (command) {
        case "bye":
            formatAndPrint(BYE);
            return false;
        case "list":
            displayList();
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
            markAsDone(index);
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
            delete(index);
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
        return true;
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
     * Formats the list of tasks for displaying when the user inputs "list".
     */
    public void displayList() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            output.append(String.format("%d. %s", i + 1, this.list.get(i)));
            // Append new line for all lines except last line.
            if (i != this.list.size() - 1) {
                output.append("\n");
            }
        }
        formatAndPrint(output.toString());
    }

    /**
     * Marks a given task as done.
     *
     * @param itemNo Index of the task in the ArrayList.
     */
    public void markAsDone(int itemNo) {
        Task task = list.get(itemNo);
        task.toggleComplete();
        formatAndPrint("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Deletes a given task
     *
     * @param itemNo Index of the task in the ArrayList.
     */
    public void delete(int itemNo) {
        Task task = list.remove(itemNo);
        formatAndPrint(String.format("Noted. I've removed this task:\n%s\nNow you have %d %s in your list.",
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
        String line = "____________________________________________________________\n";
        System.out.printf("%s%s\n%s", line, s, line);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
